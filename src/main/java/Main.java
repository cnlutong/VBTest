import application.TestManager;
import application.TestService;
import domain.WordBank;
import domain.algorithm.IRTAlgorithm;
import domain.model.Question;
import domain.model.TestResult;
import domain.model.UserModel;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WordBank wordBank = initializeWordBank("C:\\Users\\cnlut\\Desktop\\test_v5.csv");
        if (wordBank == null) {
            System.exit(1);
        }

        TestService testService = initializeTestService(wordBank);
        UserModel user = createUser();

        runTest(testService, user);

        scanner.close();
    }

    /**
     * 初始化词库
     * @return 初始化成功返回 WordBank 对象，失败返回 null
     */
    private static WordBank initializeWordBank(String pathWordBank) {
        try {
            WordBank wordBank = new WordBank(pathWordBank);
            System.out.println("词库加载成功，共 " + wordBank.getTotalWordCount() + " 个单词。");
            return wordBank;
        } catch (IOException e) {
            System.err.println("词库加载失败：" + e.getMessage());
            return null;
        }
    }

    /**
     * 初始化测试服务
     * @param wordBank 词库对象
     * @return TestService 对象
     */
    private static TestService initializeTestService(WordBank wordBank) {
        IRTAlgorithm algorithm = new IRTAlgorithm(wordBank);
        TestManager testManager = new TestManager(algorithm, wordBank);
        return new TestService(testManager);
    }

    /**
     * 创建用户模型
     * @return UserModel 对象
     */
    private static UserModel createUser() {
        System.out.print("请输入您的名字：");
        String userName = scanner.nextLine().trim();
        return new UserModel("user1", userName);
    }

    /**
     * 执行测试流程
     * @param testService 测试服务
     * @param user 用户模型
     */
    private static void runTest(TestService testService, UserModel user) {
        testService.initiateTest(user);
        int questionCount = 0;

        System.out.println("\n词汇量测试开始！");
        System.out.println("请仔细阅读每个问题，并选择最合适的答案。\n");

        while (!testService.isTestFinished()) {
            Question question = testService.provideNextQuestion();
            displayQuestion(question, ++questionCount);

            int userAnswer = getUserAnswer();
            boolean isCorrect = question.isCorrect(userAnswer - 1);
            testService.processAnswer(question, isCorrect);

            provideFeedback(question, isCorrect);
            displayStatistics(testService);
        }

        displayTestResult(testService.concludeTest());
    }

    /**
     * 显示问题
     * @param question 问题对象
     * @param questionNumber 问题编号
     */
    private static void displayQuestion(Question question, int questionNumber) {
        System.out.println("\n问题 " + questionNumber + ":");
        System.out.println(question);
    }

    /**
     * 获取用户答案
     * @return 用户输入的答案
     */
    private static int getUserAnswer() {
        while (true) {
            System.out.print("请选择正确的选项：");
            try {
                int answer = Integer.parseInt(scanner.nextLine().trim());
                if (answer >= 1 && answer <= 6) {
                    return answer;
                }
                System.out.println("无效输入，请输入1到6之间的数字。");
            } catch (NumberFormatException e) {
                System.out.println("无效输入，请输入一个数字。");
            }
        }
    }

    /**
     * 提供答题反馈
     * @param question 当前问题
     * @param isCorrect 答案是否正确
     */
    private static void provideFeedback(Question question, boolean isCorrect) {
        if (isCorrect) {
            System.out.println("回答正确！");
        } else {
            System.out.println("回答错误。正确答案是：" +
                    (question.getCorrectOptionIndex() + 1) + ". " +
                    question.getOptions().get(question.getCorrectOptionIndex()));
        }
    }

    /**
     * 显示实时统计数据
     * @param testService 测试服务
     */
    private static void displayStatistics(TestService testService) {
        System.out.println("\n--- 实时统计 ---");
        System.out.println(testService.getRealTimeStats());
    }

    /**
     * 显示测试结果
     * @param result 测试结果
     */
    private static void displayTestResult(TestResult result) {
        System.out.println("\n测试结束！");
        System.out.println(result);
    }
}