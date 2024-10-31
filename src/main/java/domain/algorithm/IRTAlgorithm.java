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
    private static final int MIN_QUESTIONS = 10;          // 最少问题数
    private static final int MAX_QUESTIONS = 80;          // 最多问题数
    private static final double MIN_DIFFICULTY = 3.0;     // 最小难度
    private static final double MAX_DIFFICULTY = 13.0;    // 最大难度
    private static final double MIN_ABILITY = 0.0;        // 最小能力值
    private static final double MAX_ABILITY = 13.0;       // 最大能力值
    private static final double INITIAL_ABILITY = 3.0;    // 初始能力值
    private static final double DIFFICULTY_SLOPE = 1.5;   // 难度斜率
    private static final double LEARNING_RATE = 0.15;     // 学习率

    // 窗口相关常量
    private static final int ANSWER_WINDOW_SIZE = 5;      // 最近答题观察数
    private static final int MAX_WRONG_ALLOWED = 2;       // 允许的最大错误数

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
        user.initAnswerRecord(ANSWER_WINDOW_SIZE);
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
            usedWords.clear();
            return selectWordByDifficulty(targetDifficulty);
        }

        return availableWords.get(random.nextInt(availableWords.size()));
    }

    private List<Word> findAvailableWordsAtDifficulty(int difficulty) {
        List<Word> wordsAtDifficulty = wordBank.getWordsByDifficulty(difficulty);
        wordsAtDifficulty.removeAll(usedWords);
        return wordsAtDifficulty;
    }

    private Question createQuestion(Word word) {
        List<String> options = wordBank.getRandomOptions(word, 4);
        int correctOptionIndex = options.indexOf(word.getChinese());
        return new Question(word, options, correctOptionIndex);
    }

    @Override
    public void updateUserModel(UserModel user, Question question, boolean isCorrect) {
        // 记录答题结果
        user.recordAnswer(isCorrect);

        if (isCorrect) {
            // 只在答对时更新能力值
            double currentEstimate = user.getAbilityEstimate();
            double questionDifficulty = question.getWord().getDifficulty();
            double probability = calculateProbability(currentEstimate, questionDifficulty);

            double adjustment = LEARNING_RATE * (1 - probability);
            double information = calculateInformation(probability);

            if (information > 1e-10) {
                double newEstimate = currentEstimate + (adjustment / information);
                user.setAbilityEstimate(Math.max(MIN_ABILITY, Math.min(MAX_ABILITY, newEstimate)));
            }
        }
    }

    /**
     * 计算回答正确的概率
     */
    private double calculateProbability(double ability, double difficulty) {
        double exponent = Math.max(-20, Math.min(20, DIFFICULTY_SLOPE * (ability - difficulty)));
        return 1 / (1 + Math.exp(-exponent));
    }

    private double calculateInformation(double probability) {
        return probability * (1 - probability);
    }

    @Override
    public boolean isTestComplete(UserModel user, List<Question> answeredQuestions) {
        // 判断是否达到最大题目数
        if (answeredQuestions.size() >= MAX_QUESTIONS) {
            return true;
        }

        // 在达到最小观察数量后，开始检查错误数
        if (answeredQuestions.size() >= ANSWER_WINDOW_SIZE) {
            // 检查最近N题中的错误数
            if (user.getRecentWrongCount() >= MAX_WRONG_ALLOWED) {
                return true;
            }
        }

        return false;
    }

    @Override
    public TestResult calculateFinalResult(UserModel user, List<Question> answeredQuestions) {
        int correctAnswers = (int) answeredQuestions.stream()
                .filter(Question::isAnsweredCorrectly)
                .count();
        double finalAbility = user.getAbilityEstimate();
        int estimatedVocabularySize = estimateVocabularySize(finalAbility);
        int totalVocabularySize = wordBank.getTotalWordCount();
        return new TestResult(user, finalAbility, answeredQuestions.size(),
                correctAnswers, estimatedVocabularySize, totalVocabularySize);
    }

    /**
     * 估算词汇量大小
     */
    private int estimateVocabularySize(double ability) {
        // 获取最低难度级别的基础词汇量
        int baseVocabulary = wordBank.getWordCountUpToDifficulty((int)MIN_DIFFICULTY);

        // 如果能力值低于最低难度,按比例计算
        if (ability <= MIN_DIFFICULTY) {
            double fraction = ability / MIN_DIFFICULTY;
            return (int) Math.round(fraction * baseVocabulary);
        }

        // 对于正常范围的能力值,在相邻难度等级之间进行插值
        int lowerDifficulty = (int) Math.floor(ability);
        int upperDifficulty = (int) Math.ceil(ability);

        int lowerCount = wordBank.getWordCountUpToDifficulty(Math.min((int)MAX_DIFFICULTY, lowerDifficulty));
        int upperCount = wordBank.getWordCountUpToDifficulty(Math.min((int)MAX_DIFFICULTY, upperDifficulty));

        // 线性插值
        double fraction = ability - lowerDifficulty;
        return (int) Math.round(lowerCount + fraction * (upperCount - lowerCount));
    }
}