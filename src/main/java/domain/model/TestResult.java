package domain.model;

/**
 * TestResult 类表示测试的最终结果。
 */
public class TestResult {
    private final UserModel user;
    private final double finalAbilityEstimate;
    private final int totalQuestions;
    private final int correctAnswers;
    private final int estimatedVocabularySize;
    private final int totalVocabularySize;
    private final ResultMean resultMean;

    /**
     * 构造一个新的 TestResult 对象。
     *
     * @param user 参与测试的用户
     * @param finalAbilityEstimate 最终能力估计值
     * @param totalQuestions 总题目数
     * @param correctAnswers 正确答题数
     * @param estimatedVocabularySize 估计词汇量大小
     * @param totalVocabularySize 词库总词汇量
     */
    public TestResult(UserModel user, double finalAbilityEstimate, int totalQuestions, int correctAnswers,
                      int estimatedVocabularySize, int totalVocabularySize) {
        this.resultMean = new ResultMean(0);
        this.user = user;
        this.finalAbilityEstimate = finalAbilityEstimate;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.estimatedVocabularySize = estimatedVocabularySize;
        this.totalVocabularySize = totalVocabularySize;
    }


    @Override
    public String toString() {
        return String.format(
                "测试结果:\n" +
                        "用户: %s\n" +
                        "最终能力估计: %.2f\n" +
                        "总题数: %d\n" +
                        "正确答题数: %d\n" +
                        "正确率: %.2f%%\n" +
                        "估计词汇量: %d 个单词\n" +
                        "词库总词汇量: %d 个单词\n" +
                        "词汇量百分比: %.2f%%\n" +
                        "估计词汇量水平: " +
                        resultMean.getResultMean(estimatedVocabularySize),
                user.getName(),
                finalAbilityEstimate,
                totalQuestions,
                correctAnswers,
                (double) correctAnswers / totalQuestions * 100,
                estimatedVocabularySize,
                totalVocabularySize,
                (double) estimatedVocabularySize / totalVocabularySize * 100
        );
    }
}