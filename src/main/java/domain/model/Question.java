package domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Question 类表示一个测试题目。
 * 包含问题的单词、选项列表和正确答案索引。
 */
public class Question {
    private final Word word;                // 问题中的单词
    private final List<String> options;     // 选项列表
    private final int correctOptionIndex;   // 正确选项的索引
    private boolean answeredCorrectly;      // 是否回答正确
    private static final String I_DONT_KNOW_OPTION = "跳过";  // "跳过"选项

    /**
     * 构造一个新的 Question 对象。
     *
     * @param word               题目中的单词
     * @param options            选项列表
     * @param correctOptionIndex 正确选项的索引
     */
    public Question(Word word, List<String> options, int correctOptionIndex) {
        this.word = word;
        this.options = new ArrayList<>(options);  // 创建可变列表
        this.options.add(I_DONT_KNOW_OPTION);  // 添加"跳过"选项
        this.correctOptionIndex = correctOptionIndex;
        this.answeredCorrectly = false;
    }

    // Getter 方法
    public Word getWord() { return word; }
    public List<String> getOptions() { return List.copyOf(options); }  // 返回不可变列表
    public int getCorrectOptionIndex() { return correctOptionIndex; }

    /**
     * 检查给定的答案是否正确。
     *
     * @param answerIndex 用户选择的答案索引
     * @return 如果答案正确返回 true，否则返回 false
     */
    public boolean isCorrect(int answerIndex) {
        if (answerIndex == options.size() - 1) {  // 如果选择了"跳过"
            this.answeredCorrectly = false;
        } else {
            this.answeredCorrectly = (answerIndex == correctOptionIndex);
        }
        return this.answeredCorrectly;
    }

    /**
     * 返回该问题是否被正确回答。
     *
     * @return 如果问题被正确回答返回 true，否则返回 false
     */
    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    /**
     * 返回问题的字符串表示，用于显示给用户。
     *
     * @return 包含英文单词和选项的字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("英文单词: ").append(word.getWord()).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}