package domain.algorithm;

import domain.WordBank;
import domain.model.Question;
import domain.model.TestResult;
import domain.model.UserModel;
import domain.model.Word;

import java.util.*;

/**
 * 基于项目反应理论（IRT）的测试算法实现类
 */
public class IRTAlgorithm implements TestAlgorithm {
    private final WordBank wordBank;
    private final Random random;
    private final Set<Word> usedWords;

    // 定义算法相关的常量
    private static final int MIN_QUESTIONS = 10;  // 最少问题数
    private static final int MAX_QUESTIONS = 80;  // 最多问题数
    private static final int STABILITY_CHECK_INTERVAL = 3;  // 稳定性检查间隔
    private static final double MIN_DIFFICULTY = 3.0;  // 最小难度
    private static final double MAX_DIFFICULTY = 13.0;  // 最大难度
    private static final double MIN_ABILITY = 3.0;  // 最小能力值
    private static final double MAX_ABILITY = 13.0;  // 最大能力值
    private static final double INITIAL_ABILITY = 3.0;  // 初始能力值
    private static final double DIFFICULTY_SLOPE = 1.0;  // 难度斜率
    private static final double LEARNING_RATE = 0.2;  // 学习率
    private static final double ABILITY_STABILITY_THRESHOLD = 0.05;  // 能力稳定性阈值

    /**
     * 构造函数
     * @param wordBank 词库对象
     */
    public IRTAlgorithm(WordBank wordBank) {
        this.wordBank = wordBank;
        this.random = new Random();
        this.usedWords = new HashSet<>();
    }

    /**
     * 初始化用户模型
     * @param user 用户模型对象
     */
    public void initializeUser(UserModel user) {
        user.setAbilityEstimate(INITIAL_ABILITY);
    }

    @Override
    public Question selectNextQuestion(UserModel user, List<Question> answeredQuestions) {
        double targetDifficulty = user.getAbilityEstimate();
        Word word = selectWordByDifficulty(targetDifficulty);
        usedWords.add(word);
        return createQuestion(word);
    }

    /**
     * 根据目标难度选择单词
     * @param targetDifficulty 目标难度
     * @return 选中的单词
     */
    private Word selectWordByDifficulty(double targetDifficulty) {
        int roundedDifficulty = (int) Math.round(Math.max(MIN_DIFFICULTY, Math.min(MAX_DIFFICULTY, targetDifficulty)));
        List<Word> availableWords = findAvailableWordsAtDifficulty(roundedDifficulty);

        if (availableWords.isEmpty()) {
            // 如果在目标难度没有可用单词，逐步扩大搜索范围
            for (int i = 1; i <= 5; i++) {
                if (roundedDifficulty - i >= MIN_DIFFICULTY) {
                    availableWords = findAvailableWordsAtDifficulty(roundedDifficulty - i);
                    if (!availableWords.isEmpty()) break;
                }
                if (roundedDifficulty + i <= MAX_DIFFICULTY) {
                    availableWords = findAvailableWordsAtDifficulty(roundedDifficulty + i);
                    if (!availableWords.isEmpty()) break;
                }
            }
        }

        if (availableWords.isEmpty()) {
            // 如果仍然没有可用单词，重置已使用单词集合并重新尝试
            usedWords.clear();
            return selectWordByDifficulty(targetDifficulty);
        }

        return availableWords.get(random.nextInt(availableWords.size()));
    }

    /**
     * 查找指定难度下的可用单词
     * @param difficulty 难度值
     * @return 可用单词列表
     */
    private List<Word> findAvailableWordsAtDifficulty(int difficulty) {
        List<Word> wordsAtDifficulty = wordBank.getWordsByDifficulty(difficulty);
        wordsAtDifficulty.removeAll(usedWords);
        return wordsAtDifficulty;
    }

    /**
     * 创建问题
     * @param word 选中的单词
     * @return 创建的问题
     */
    private Question createQuestion(Word word) {
        List<String> options = wordBank.getRandomOptions(word, 4);
        int correctOptionIndex = options.indexOf(word.getChinese());
        return new Question(word, options, correctOptionIndex);
    }

    @Override
    public void updateUserModel(UserModel user, Question question, boolean isCorrect) {
        double currentEstimate = user.getAbilityEstimate();
        double questionDifficulty = question.getWord().getDifficulty();

        double probability = calculateProbability(currentEstimate, questionDifficulty);
        double information = calculateInformation(probability);
        double adjustment = LEARNING_RATE * ((isCorrect ? 1 : 0) - probability);

        if (information > 1e-10) {
            double newEstimate = currentEstimate + (adjustment / information);
            user.setAbilityEstimate(Math.max(MIN_ABILITY, Math.min(MAX_ABILITY, newEstimate)));
        }
    }

    /**
     * 计算回答正确的概率
     * @param ability 能力值
     * @param difficulty 难度值
     * @return 回答正确的概率
     */
    private double calculateProbability(double ability, double difficulty) {
        double exponent = Math.max(-20, Math.min(20, DIFFICULTY_SLOPE * (ability - difficulty)));
        return 1 / (1 + Math.exp(-exponent));
    }

    /**
     * 计算信息量
     * @param probability 概率
     * @return 信息量
     */
    private double calculateInformation(double probability) {
        return probability * (1 - probability);
    }

    @Override
    public boolean isTestComplete(UserModel user, List<Question> answeredQuestions) {
        int questionCount = answeredQuestions.size();
        if (questionCount < MIN_QUESTIONS) return false;
        if (questionCount >= MAX_QUESTIONS) return true;
        return questionCount % STABILITY_CHECK_INTERVAL == 0 && isAbilityEstimateStable(answeredQuestions);
    }

    /**
     * 检查能力估计是否稳定
     * @param answeredQuestions 已回答的问题列表
     * @return 能力估计是否稳定
     */
    private boolean isAbilityEstimateStable(List<Question> answeredQuestions) {
        if (answeredQuestions.size() < STABILITY_CHECK_INTERVAL) return false;

        int startIndex = Math.max(0, answeredQuestions.size() - STABILITY_CHECK_INTERVAL);
        double sumAbility = 0;
        for (int i = startIndex; i < answeredQuestions.size(); i++) {
            sumAbility += answeredQuestions.get(i).getWord().getDifficulty();
        }
        double averageAbility = sumAbility / STABILITY_CHECK_INTERVAL;

        double variance = 0;
        for (int i = startIndex; i < answeredQuestions.size(); i++) {
            double diff = answeredQuestions.get(i).getWord().getDifficulty() - averageAbility;
            variance += diff * diff;
        }
        variance /= STABILITY_CHECK_INTERVAL;

        return Math.sqrt(variance) < ABILITY_STABILITY_THRESHOLD;
    }

    @Override
    public TestResult calculateFinalResult(UserModel user, List<Question> answeredQuestions) {
        int correctAnswers = (int) answeredQuestions.stream().filter(Question::isAnsweredCorrectly).count();
        double finalAbility = user.getAbilityEstimate();
        int estimatedVocabularySize = estimateVocabularySize(finalAbility);
        int totalVocabularySize = wordBank.getTotalWordCount();
        return new TestResult(user, finalAbility, answeredQuestions.size(), correctAnswers, estimatedVocabularySize, totalVocabularySize);
    }

    /**
     * 估算词汇量大小
     * @param ability 能力值
     * @return 估算的词汇量大小
     */
    private int estimateVocabularySize(double ability) {
        int lowerDifficulty = (int) Math.floor(ability);
        int upperDifficulty = (int) Math.ceil(ability);

        int lowerCount = wordBank.getWordCountUpToDifficulty((int) Math.max(MIN_DIFFICULTY, Math.min(MAX_DIFFICULTY, lowerDifficulty)));
        int upperCount = wordBank.getWordCountUpToDifficulty((int) Math.max(MIN_DIFFICULTY, Math.min(MAX_DIFFICULTY, upperDifficulty)));

        double fraction = ability - lowerDifficulty;
        return (int) Math.round(lowerCount + fraction * (upperCount - lowerCount));
    }
}