package domain.model;

/**
 * UserModel 类表示参与测试的用户。
 * 包含用户的基本信息和能力估计值。
 */
public class UserModel {
    private final String id;            // 用户的唯一标识符
    private final String name;          // 用户名
    private double abilityEstimate;     // 用户能力估计值

    /**
     * 构造一个新的 UserModel 对象。
     *
     * @param id   用户的唯一标识符
     * @param name 用户名
     */
    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
        this.abilityEstimate = 3.0; // 初始能力值
    }

    // Getter 和 Setter 方法
    public String getId() { return id; }
    public String getName() { return name; }
    public double getAbilityEstimate() { return abilityEstimate; }
    public void setAbilityEstimate(double abilityEstimate) { this.abilityEstimate = abilityEstimate; }

    /**
     * 返回用户模型的字符串表示。
     *
     * @return 包含用户信息的字符串
     */
    @Override
    public String toString() {
        return String.format("UserModel{id='%s', name='%s', abilityEstimate=%.2f}", id, name, abilityEstimate);
    }
}