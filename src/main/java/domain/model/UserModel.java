package domain.model;

/**
 * UserModel 类表示参与测试的用户。
 * 包含用户的基本信息、能力估计值和最近答题记录。
 */
public class UserModel {
    private final String id; // 用户的唯一标识符
    private final String name; // 用户名
    private double abilityEstimate; // 用户能力估计值
    private int[] recentAnswers; // 最近答题记录
    private int totalAnswers; // 总答题数
    private int windowSize; // 观察窗口大小

    /**
     * 构造一个新的 UserModel 对象。
     *
     * @param id   用户的唯一标识符
     * @param name 用户名
     */
    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
        this.abilityEstimate = 0.0; // 初始能力值
        this.windowSize = 6; // 默认窗口大小
        this.recentAnswers = new int[windowSize];
        this.totalAnswers = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAbilityEstimate() {
        return abilityEstimate;
    }

    public void setAbilityEstimate(double abilityEstimate) {
        this.abilityEstimate = abilityEstimate;
    }

    /**
     * 初始化答题记录
     * 
     * @param windowSize 窗口大小
     */
    public void initAnswerRecord(int windowSize) {
        if (this.windowSize != windowSize) {
            this.windowSize = windowSize;
            this.recentAnswers = new int[windowSize];
        }
        this.totalAnswers = 0;
    }

    /**
     * 记录一次答题结果
     * 
     * @param isCorrect 是否答对
     */
    public void recordAnswer(boolean isCorrect) {
        // 如果数组已满，向前移动所有元素
        if (totalAnswers >= windowSize) {
            System.arraycopy(recentAnswers, 1, recentAnswers, 0, windowSize - 1);
            recentAnswers[windowSize - 1] = isCorrect ? 1 : 0;
        } else {
            // 如果数组未满，直接添加到当前位置
            recentAnswers[totalAnswers] = isCorrect ? 1 : 0;
            totalAnswers++;
        }
    }

    /**
     * 获取最近N题中的错误数量
     * 
     * @return 错误数量
     */
    public int getRecentWrongCount() {
        int wrongCount = 0;
        int count = Math.min(totalAnswers, windowSize);
        for (int i = 0; i < count; i++) {
            if (recentAnswers[i] == 0) {
                wrongCount++;
            }
        }
        return wrongCount;
    }

    /**
     * 获取当前已答题数量（用于判断窗口是否已满）
     * 
     * @return 已答题数量
     */
    public int getAnsweredCount() {
        return totalAnswers;
    }

    /**
     * 获取窗口大小
     * 
     * @return 窗口大小
     */
    public int getWindowSize() {
        return windowSize;
    }

    // New fields for initial ability tracking
    private double initialAbility = 0.0;
    private boolean customInitialAbility = false;

    public void setInitialAbility(double initialAbility) {
        this.initialAbility = initialAbility;
    }

    public double getInitialAbility() {
        return initialAbility;
    }

    public void setCustomInitialAbility(boolean customInitialAbility) {
        this.customInitialAbility = customInitialAbility;
    }

    public boolean isCustomInitialAbility() {
        return customInitialAbility;
    }

    @Override
    public String toString() {
        return String.format(
                "UserModel{id='%s', name='%s', abilityEstimate=%.2f, recentWrongCount=%d, answeredCount=%d, initialAbility=%.2f, customInitial=%b}",
                id, name, abilityEstimate, getRecentWrongCount(), getAnsweredCount(), initialAbility,
                customInitialAbility);
    }
}