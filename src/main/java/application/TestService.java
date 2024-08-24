package application;

import domain.model.Question;
import domain.model.TestResult;
import domain.model.UserModel;

/**
 * TestService 类封装测试的业务逻辑，为外部提供简单的接口。
 */
public class TestService {
    private final TestManager testManager;

    public TestService(TestManager testManager) {
        this.testManager = testManager;
    }

    public void initiateTest(UserModel user) {
        testManager.startTest(user);
    }

    public Question provideNextQuestion() {
        return testManager.getNextQuestion();
    }

    public void processAnswer(Question question, boolean isCorrect) {
        testManager.submitAnswer(question, isCorrect);
    }

    public boolean isTestFinished() {
        return testManager.isTestComplete();
    }

    public TestResult concludeTest() {
        return testManager.finishTest();
    }

    public String getRealTimeStats() {
        return testManager.getRealTimeStats();
    }
}