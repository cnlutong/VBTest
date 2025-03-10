package domain;

import domain.model.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * WordBank 类负责管理词库，包括加载、统计和提供单词。
 */
public class WordBank {
    private final List<Word> words;
    private final Map<Integer, Integer> wordCountByDifficulty;
    private final Random random;
    private final String CSV_FILE_PATH;

    /**
     * 构造一个新的 WordBank 对象并加载词库。
     *
     * @throws IOException 如果无法读取 CSV 文件
     */
    public WordBank(String pathWorBank) throws IOException {
        this.words = new ArrayList<>();
        this.wordCountByDifficulty = new HashMap<>();
        this.random = new Random();
        this.CSV_FILE_PATH = pathWorBank;
        loadWordsFromCSV();
        calculateWordCounts();

    }
    /**
     * 从 CSV 文件加载单词。
     *
     * @throws IOException 如果无法读取 CSV 文件
     */
    private void loadWordsFromCSV() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = parseCSVLine(line);
                if (values.length == 3) {
                    String word = values[0].replaceAll("\"", "").trim();
                    String chinese = values[1].replaceAll("\"", "").trim();
                    try {
                        int difficulty = Integer.parseInt(values[2].trim());
                        words.add(new Word(String.valueOf(words.size() + 1), word, chinese, difficulty));
                    } catch (NumberFormatException e) {
                        System.err.println("解析难度值时出错，跳过该行: " + line);
                    }
                } else {
                    System.err.println("CSV 行格式不正确，跳过该行: " + line);
                }
            }
        }

        if (words.isEmpty()) {
            throw new IOException("没有从 CSV 文件中读取到有效的单词数据。");
        }
    }

    /**
     * 解析 CSV 行，考虑引号内的逗号。
     *
     * @param line CSV 行
     * @return 解析后的字段数组
     */
    private String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else if (c == '"') {
                inQuotes = !inQuotes;
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

    /**
     * 计算每个难度级别的单词数量。
     */
    private void calculateWordCounts() {
        for (Word word : words) {
            int difficulty = word.getDifficulty();
            wordCountByDifficulty.put(difficulty, wordCountByDifficulty.getOrDefault(difficulty, 0) + 1);
        }
    }

    /**
     * 获取词库中的总单词数量。
     *
     * @return 总单词数量
     */
    public int getTotalWordCount() {
        return words.size();
    }


    /**
     * 获取直到指定难度级别的累计单词数量。
     *
     * @param maxDifficulty 最大难度级别
     * @return 累计单词数量
     */
    public int getWordCountUpToDifficulty(int maxDifficulty) {
        return wordCountByDifficulty.entrySet().stream()
                .filter(entry -> entry.getKey() <= maxDifficulty)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    /**
     * 根据难度获取单词。
     *
     * @param difficulty 目标难度
     * @return 指定难度的单词列表
     */
    public List<Word> getWordsByDifficulty(int difficulty) {
        return words.stream()
                .filter(word -> word.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    /**
     * 生成包含正确答案和相近难度干扰项的选项列表
     * @param correctWord 正确单词
     * @param optionCount 选项数量
     * @return 打乱顺序的选项列表
     */
    public List<String> getRandomOptions(Word correctWord, int optionCount) {
        List<String> options = new ArrayList<>(optionCount);
        options.add(correctWord.getChinese());

        List<Word> sameLevel = new ArrayList<>();
        int difficulty = correctWord.getDifficulty();

        // 逐步扩大难度范围直到获得足够的干扰项
        for (int range = 0; range <= 2 && sameLevel.size() < optionCount * 2; range++) {
            if (difficulty - range > 0) {
                sameLevel.addAll(getWordsByDifficulty(difficulty - range));
            }
            if (range > 0 && difficulty + range <= 12) {
                sameLevel.addAll(getWordsByDifficulty(difficulty + range));
            }
        }

        sameLevel.remove(correctWord);
        Collections.shuffle(sameLevel);

        sameLevel.stream()
                .map(Word::getChinese)
                .distinct()
                .limit(optionCount - 1)
                .forEach(options::add);

        Collections.shuffle(options);
        return options;
    }

    /**
     * 获取指定数量的干扰项（不包含正确答案）
     * @param correctWord 正确单词
     * @param optionCount 需要的选项数量
     * @return 干扰项列表
     */
    public List<String> getRandomDistractors(Word correctWord, int optionCount) {
        List<String> distractors = new ArrayList<>();
        // 获取同等级的单词列表
        List<Word> wordsAtLevel = getWordsByDifficulty(correctWord.getDifficulty());

        while (distractors.size() < optionCount) {
            Word randomWord = wordsAtLevel.get(random.nextInt(wordsAtLevel.size()));
            if (!randomWord.getChinese().equals(correctWord.getChinese())
                    && !distractors.contains(randomWord.getChinese())) {
                distractors.add(randomWord.getChinese());
            }
        }
        return distractors;
    }
}