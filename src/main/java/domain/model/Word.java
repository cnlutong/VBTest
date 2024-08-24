package domain.model;

/**
 * Word 类表示词库中的一个单词条目。
 */
public class Word {
    private final String id;        // 单词的唯一标识符
    private final String word;      // 英文单词
    private final String chinese;   // 中文翻译
    private final int difficulty;   // 难度等级（3-13）

    /**
     * 构造一个新的 Word 对象。
     *
     * @param id         单词的唯一标识符
     * @param word       英文单词
     * @param chinese    中文翻译
     * @param difficulty 难度等级
     */
    public Word(String id, String word, String chinese, int difficulty) {
        this.id = id;
        this.word = word;
        this.chinese = chinese;
        this.difficulty = difficulty;
    }

    // Getter 方法
    public String getId() { return id; }
    public String getWord() { return word; }
    public String getChinese() { return chinese; }
    public int getDifficulty() { return difficulty; }

    @Override
    public String toString() {
        return String.format("Word{id='%s', word='%s', chinese='%s', difficulty=%d}", id, word, chinese, difficulty);
    }
}