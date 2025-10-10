package domain.algorithm;

import domain.model.Question;
import domain.model.TestResult;
import domain.model.UserModel;

import java.util.List;

/**
 * TestAlgorithm 接口定义了测试算法的核心方法。
 * 实现此接口的类需要提供具体的测试逻辑，如题目选择、用户模型更新等。
 */
public interface TestAlgorithm {
    /**
     * 初始化用户模型。
     *
     * @param user 要初始化的用户
     */
    void initializeUser(UserModel user);

    /**
     * 选择下一个问题。
     *
     * @param user              当前用户
     * @param answeredQuestions 已回答的问题列表
     * @return 下一个 Question 对象
     */
    Question selectNextQuestion(UserModel user, List<Question> answeredQuestions);

    /**
     * 更新用户模型。
     *
     * @param user      当前用户
     * @param question  刚回答的问题
     * @param isCorrect 答案是否正确
     */
    void updateUserModel(UserModel user, Question question, boolean isCorrect);

    /**
     * 检查测试是否完成。
     *
     * @param user              当前用户
     * @param answeredQuestions 已回答的问题列表
     * @return 如果测试完成返回 true，否则返回 false
     */
    boolean isTestComplete(UserModel user, List<Question> answeredQuestions);

    /**
     * 计算最终结果。
     *
     * @param user              当前用户
     * @param answeredQuestions 已回答的问题列表
     * @return TestResult 对象
     */
    TestResult calculateFinalResult(UserModel user, List<Question> answeredQuestions);
}