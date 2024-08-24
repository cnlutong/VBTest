package application;

import domain.WordBank;
import domain.algorithm.TestAlgorithm;
import domain.model.Question;
import domain.model.TestResult;
import domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * TestManager 类负责管理整个测试流程，协调各个组件的工作。
 */
public class TestManager {
    private final TestAlgorithm algorithm;
    private final WordBank wordBank;
    private UserModel currentUser;
    private final List<Question> answeredQuestions;
    private int correctAnswersCount;

    /**
     * 构造一个新的 TestManager 对象。
     *
     * @param algorithm 测试算法
     * @param wordBank 词库
     */
    public TestManager(TestAlgorithm algorithm, WordBank wordBank) {
        this.algorithm = algorithm;
        this.wordBank = wordBank;
        this.answeredQuestions = new ArrayList<>();
        this.correctAnswersCount = 0;
    }

    /**
     * 开始新的测试。
     *
     * @param user 参与测试的用户
     */
    public void startTest(UserModel user) {
        this.currentUser = user;
        this.answeredQuestions.clear();
        this.correctAnswersCount = 0;
    }

    /**
     * 获取下一个问题。
     *
     * @return 下一个 Question 对象
     */
    public Question getNextQuestion() {
        return algorithm.selectNextQuestion(currentUser, answeredQuestions);
    }

    /**
     * 提交答案。
     *
     * @param question 当前问题
     * @param isCorrect 答案是否正确
     */
    public void submitAnswer(Question question, boolean isCorrect) {
        answeredQuestions.add(question);
        if (isCorrect) {
            correctAnswersCount++;
        }
        algorithm.updateUserModel(currentUser, question, isCorrect);
    }

    /**
     * 检查测试是否完成。
     *
     * @return 如果测试完成返回 true，否则返回 false
     */
    public boolean isTestComplete() {
        return algorithm.isTestComplete(currentUser, answeredQuestions);
    }

    /**
     * 完成测试并返回结果。
     *
     * @return TestResult 对象
     */
    public TestResult finishTest() {
        return algorithm.calculateFinalResult(currentUser, answeredQuestions);
    }

    /**
     * 获取实时统计数据。
     *
     * @return 包含当前统计信息的字符串
     */
    public String getRealTimeStats() {
        double correctRate = answeredQuestions.isEmpty() ? 0 : (double) correctAnswersCount / answeredQuestions.size() * 100;

        return String.format("当前题目数：%d\n当前能力估计：%.2f\n当前正确率：%.2f%%",
                answeredQuestions.size(), currentUser.getAbilityEstimate(), correctRate);
    }
}