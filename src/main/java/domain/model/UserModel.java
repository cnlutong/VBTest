package domain.model;

/**
 * UserModel 类表示参与测试的用户。
 * 包含用户的基本信息、能力估计值和连续答错次数的跟踪。
 */
public class UserModel {
    private final String id;                // 用户的唯一标识符
    private final String name;              // 用户名
    private double abilityEstimate;         // 用户能力估计值
    private int consecutiveWrongAnswers;    // 连续答错次数

    /**
     * 构造一个新的 UserModel 对象。
     *
     * @param id   用户的唯一标识符
     * @param name 用户名
     */
    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
        this.abilityEstimate = 3.0;        // 初始能力值
        this.consecutiveWrongAnswers = 0;   // 初始连续答错次数
    }

    // 基本信息的 Getter 方法
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 能力估计值的 Getter 和 Setter 方法
    public double getAbilityEstimate() {
        return abilityEstimate;
    }

    public void setAbilityEstimate(double abilityEstimate) {
        this.abilityEstimate = abilityEstimate;
    }

    // 连续答错相关的方法
    /**
     * 获取当前连续答错次数
     * @return 连续答错次数
     */
    public int getConsecutiveWrongAnswers() {
        return consecutiveWrongAnswers;
    }

    /**
     * 增加连续答错次数
     */
    public void incrementWrongAnswers() {
        consecutiveWrongAnswers++;
    }

    /**
     * 重置连续答错次数为0
     * 在答对题目时调用
     */
    public void resetWrongAnswers() {
        consecutiveWrongAnswers = 0;
    }

    /**
     * 返回用户模型的字符串表示。
     * 包含用户ID、姓名、能力估计值和连续答错次数。
     *
     * @return 包含用户信息的字符串
     */
    @Override
    public String toString() {
        return String.format("UserModel{id='%s', name='%s', abilityEstimate=%.2f, consecutiveWrongAnswers=%d}",
                id, name, abilityEstimate, consecutiveWrongAnswers);
    }
}