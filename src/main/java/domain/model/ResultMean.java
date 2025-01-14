package domain.model;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ResultMean {

    int version = 0;

    public ResultMean(int version) {
        this.version = version;
    }

    // 返回海报中的词汇量水平
    public String getGradeLevel(int estimatedVocab) {
        int correctedVocab = correctVocabEstimate(estimatedVocab);
        return getGradeLevelVersion0(correctedVocab);
    }

    // 返回成绩中的成绩解读
    public String getResultMean(int estimatedVocab) {
        int correctedVocab = correctVocabEstimate(estimatedVocab);
        return getResultInterpretation(correctedVocab);
    }

    // 返回成绩中的学习建议
    public String getResultBucket(int estimatedVocab) {
        int correctedVocab = correctVocabEstimate(estimatedVocab);
        return getLearningBucket(correctedVocab);
    }


    public int correctVocabEstimate(int estimatedVocab) {

        // 随机波动范围为±1.5%
        double randomFactorPercentage = 0.015;
        // 生成-1.5%到+1.5%之间的随机因子
        double randomFactor = (Math.random() * 2 * randomFactorPercentage) - randomFactorPercentage;
        // 应用随机因子计算最终分数
        double finalScore = estimatedVocab * (1 + randomFactor);

        return (int) finalScore;
    }


    private String getResultInterpretation(int estimatedVocab) {
        // 根据新的阶段划分标准，为不同词汇量设置相应的结果解读
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        // >=6000：大学六级水平
        vocabLevels.put(6000,
                "经评估，您的词汇量已达到大学六级水平，能熟练理解学术材料并进行较高阶英语表达。建议进一步扩充专业领域词汇，持续强化学术写作与交流能力。");

        // 5000-5999：大学四级进阶水平
        vocabLevels.put(5000,
                "经评估，您的词汇量已达到大学四级进阶水平，能较好应对较高难度阅读和听说场景。建议继续积累学术词汇，注重批判性阅读与写作训练。");

        // 4500-4999：大学四级水平
        vocabLevels.put(4500,
                "经评估，您的词汇量已达到大学四级水平，具备在一般学术环境和日常交流中的应用能力。建议强化四级核心词汇与语法，提升综合理解力和写作深度。");

        // 3500-4499：高中高级水平
        vocabLevels.put(3500,
                "经评估，您的词汇量已达到高中高级水平，掌握了较丰富的高中词汇与语法。建议加强长难句分析与写作训练，为进入大学英语学习打好基础。");

        // 3000-3499：高中中级水平
        vocabLevels.put(3000,
                "经评估，您的词汇量已达到高中中级水平，能理解较为复杂的题材和文章。建议继续加强听说读写训练，积累高频词汇，为更高阶段学习做准备。");

        // 2000-2999：高中初级水平
        vocabLevels.put(2000,
                "经评估，您的词汇量已达到高中初级水平，具备一定的高中英语基础。建议在巩固核心词汇和语法的同时，加强阅读和写作能力，以逐步提升综合水平。");

        // 1600-1999：初中高级水平
        vocabLevels.put(1600,
                "经评估，您的词汇量已达到初中高级水平，能应对大部分初中阅读和听说材料。建议继续积累常见词汇，加强书面表达与听说练习，以平稳衔接高中学习。");

        // 1300-1599：初中中级水平
        vocabLevels.put(1300,
                "经评估，您的词汇量已达到初中中级水平，对初中英语教材和基础练习有较好理解。建议夯实语法与词汇，多运用所学进行口语交流，提升综合应用能力。");

        // 1000-1299：初中初级水平
        vocabLevels.put(1000,
                "经评估，您的词汇量已达到初中初级水平，掌握了初步的初中基础词汇和语法。建议继续扩充常用词汇，加强听说读写全方位训练，为更高级别的学习打下基础。");

        // 500-999：小学高级水平
        vocabLevels.put(500,
                "经评估，您的词汇量已达到小学高级水平，对小学阶段常见单词和短语有一定掌握。建议逐步尝试更长句型和阅读材料，并保持听说练习，打牢语言基础。");

        // 300-499：小学中级水平
        vocabLevels.put(300,
                "经评估，您的词汇量已达到小学中级水平，具备一定的基础词汇量。建议通过多样化学习素材（如故事、游戏）来扩大词汇，培养口语与听力兴趣。");

        // 50-299：小学初级水平
        vocabLevels.put(50,
                "经评估，您的词汇量已达到小学初级水平，正处于初步积累阶段。建议通过图文并茂、趣味性的方式强化记忆，多进行简单口语互动，逐步建立学习信心。");

        // 0-49：学龄前水平
        vocabLevels.put(0,
                "经评估，您正处于英语启蒙阶段。建议从字母和简单的生活词汇开始，通过动画、儿歌和游戏培养语言兴趣和语感，为后续学习打下良好基础。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }



    private String getLearningBucket(int estimatedVocab) {
        // 使用新的阶段划分标准

        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // >=6000：大学六级水平
        learningBuckets.put(6000,
                "已达到大学六级水平，继续扩展专业领域词汇和学术用语，深化批判性思维训练。可多读英文原版书籍与学术论文，每周完成1-2篇深度写作和口头表达练习。");

        // 5000-5999：大学四级进阶水平
        learningBuckets.put(5000,
                "大学四级进阶阶段，重点巩固四级核心词汇并向专业词汇过渡。坚持做真题和综合训练，每天积累8-10个学术表达，每周完成1-2篇较深入的读写练习。");

        // 4500-4999：大学四级水平
        learningBuckets.put(4500,
                "大学四级水平，需系统掌握四级高频词汇和语法，强化听力和阅读理解。通过真题和模拟练习提升解题速度和准确度，每天巩固词汇并定期进行写作训练。");

        // 3500-4499：高中高级水平
        learningBuckets.put(3500,
                "高中高级阶段，强化高考词汇与复杂语法，注重长难句分析。可结合高考真题与模拟题加强阅读和写作，定期进行听力训练，每天坚持背记重点词组。");

        // 3000-3499：高中中级水平
        learningBuckets.put(3000,
                "高中中级阶段，继续积累高考必备词汇和语法点，注重语篇理解与写作思路。可结合专项训练提升阅读速度，每天完成听力与口语练习，夯实综合能力。");

        // 2000-2999：高中初级水平
        learningBuckets.put(2000,
                "高中初级阶段，需夯实必备核心词汇与基础语法，培养整体解题思路。可通过阶段性模拟测试查漏补缺，每周完成1-2次写作和听力训练，逐步强化阅读技巧。");

        // 1600-1999：初中高级水平
        learningBuckets.put(1600,
                "初中高级阶段，结合中考词汇与语法要点强化重点难点。通过真题训练提升解题能力，每天坚持听力与口语练习，培养基本的篇章写作意识。");

        // 1300-1599：初中中级水平
        learningBuckets.put(1300,
                "初中中级阶段，积累常见词汇与短语，熟悉常用语法结构。可通过课后练习与简单阅读材料提高理解能力，每周进行听说巩固并适度加强写作训练。");

        // 1000-1299：初中初级水平
        learningBuckets.put(1000,
                "初中初级阶段，重点掌握初中常见单词与基础语法。可每天进行单词听写与短篇阅读，多做口语互动练习，逐步增强词汇应用与表达能力。");

        // 500-999：小学高级水平
        learningBuckets.put(500,
                "小学高级阶段，继续积累常见单词和基础短语，尝试较长句型。通过情景对话和趣味阅读提升学习兴趣，可每周进行小测验并培养简单写作意识。");

        // 300-499：小学中级水平
        learningBuckets.put(300,
                "小学中级阶段，巩固核心基础词汇，练习基本语法和短句表达。可通过游戏、儿歌等方式维持兴趣，并尝试阅读绘本或简单故事，提高语言感知度。");

        // 50-299：小学初级水平
        learningBuckets.put(50,
                "小学初级阶段，学习简单日常词汇与短语，注重发音和拼读规则。可利用图文并茂的素材培养兴趣，每天进行少量口语及听力互动，逐步建立学习自信。");

        // 0-49：学龄前水平
        learningBuckets.put(0,
                "学龄前阶段，以培养对英语的兴趣为主，从字母和基础词汇开始。多使用动画、儿歌和游戏，帮助建立语感，每天进行5-10分钟简单互动即可。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }



    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        // 按照新标准设置等级
//        gradeLevels.put(5500, "大学四级+水平");
//        gradeLevels.put(4500, "大学四级进阶水平");
//        gradeLevels.put(3500, "大学四级入门水平");
//        gradeLevels.put(3000, "高中高级水平");
//        gradeLevels.put(2000, "高中中级水平");
//        gradeLevels.put(1600, "高中初级水平");
//        gradeLevels.put(1300, "初中高级水平");
//        gradeLevels.put(1000, "初中中级水平");
//        gradeLevels.put(700, "初中初级水平");
//        gradeLevels.put(500, "小学高级水平");
//        gradeLevels.put(300, "小学中级水平");
//        gradeLevels.put(50, "小学初级水平");
//        gradeLevels.put(0, "学龄前水平");

        gradeLevels.put(6000, "大学六级水平");
        gradeLevels.put(5000, "大学四级进阶水平");
        gradeLevels.put(4500, "大学四级水平");
        gradeLevels.put(3500, "高中高级水平");
        gradeLevels.put(2800, "高中中级水平");
        gradeLevels.put(2000, "高中初级水平");
        gradeLevels.put(1600, "初中高级水平");
        gradeLevels.put(1200, "初中中级水平");
        gradeLevels.put(900, "初中初级水平");
        gradeLevels.put(500, "小学高级水平");
        gradeLevels.put(300, "小学中级水平");
        gradeLevels.put(50, "小学初级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}