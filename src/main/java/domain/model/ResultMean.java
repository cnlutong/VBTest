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
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        // 5500及以上：大学四级+
        vocabLevels.put(5500, "经评估，您的词汇量达到大学四级+水平，具备扎实的学术英语基础。您能流畅理解专业文献，进行学术交流和写作。建议持续扩充专业词汇，提升学术表达的准确性。");

        // 4500-5499：大学四级进阶
        vocabLevels.put(4500, "经评估，您的词汇量达到大学四级进阶水平，具备良好的英语综合应用能力。您能较好地理解英语原版材料，进行日常交流。建议继续夯实学术词汇，强化听说读写能力。");

        // 3500-4499：大学四级入门
        vocabLevels.put(3500, "经评估，您的词汇量达到大学四级入门水平，已具备基础的大学英语学习能力。您能理解一般性英语材料。建议系统扩充四级词汇，加强学术英语的学习。");

        // 3000-3499：高中高级
        vocabLevels.put(3000, "经评估，您的词汇量达到高中高级水平，掌握了较丰富的高考词汇。您能应对高考类型的阅读材料和题型。建议继续扩充词汇量，为大学英语学习做准备。");

        // 2000-2999：高中中级
        vocabLevels.put(2000, "经评估，您的词汇量达到高中中级水平，具备较好的高中英语基础。您能理解一般难度的文章。建议强化高考词汇的积累，提升应用能力。");

        // 1600-1999：高中初级
        vocabLevels.put(1600, "经评估，您的词汇量达到高中初级水平，已具备基础的高中英语学习能力。您能理解基础性文章。建议系统性扩充高中词汇，培养良好的学习习惯。");

        // 1300-1599：初中高级
        vocabLevels.put(1300, "经评估，您的词汇量达到初中高级水平，掌握了较多的中考词汇。您能理解初中课本和基础英语材料。建议继续积累词汇，为高中英语学习打好基础。");

        // 1000-1299：初中中级
        vocabLevels.put(1000, "经评估，您的词汇量达到初中中级水平，具备良好的初中英语基础。您能理解日常表达和简单文章。建议加强中考词汇的积累和运用。");

        // 700-999：初中初级
        vocabLevels.put(700, "经评估，您的词汇量达到初中初级水平，已具备基础的初中英语学习能力。您能理解简单的课文内容。建议系统扩充初中词汇，培养语言应用能力。");

        // 300-699：小学高级
        vocabLevels.put(500, "经评估，您的词汇量达到小学高级水平，掌握了较多基础词汇。您能理解简单的英语日常用语。建议继续扩充词汇量，为初中英语学习做准备。");

        // 200-299：小学中级
        vocabLevels.put(300, "经评估，您的词汇量达到小学中级水平，已积累了一定的基础词汇。您能理解简单的课本内容。建议通过多样化学习继续积累词汇。");

        // 50-199：小学初级
        vocabLevels.put(50, "经评估，您的词汇量达到小学初级水平，正处于基础词汇积累阶段。建议通过生动有趣的方式学习词汇，培养学习兴趣。");

        // 0-49：学龄前
        vocabLevels.put(0, "经评估，您正处于英语学习的启蒙阶段。建议从字母和基础单词开始，通过趣味性的方式培养语感和学习兴趣。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }


    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // 5500及以上：大学四级+
        learningBuckets.put(5500, "重点扩展专业领域词汇和学术用语，强化学术写作技能。通过研究论文和专业文献提升阅读理解深度，进行高难度听力训练以适应各种语境。每周完成2-3篇学术文章精读和写作练习。");

        // 4500-5499：大学四级进阶
        learningBuckets.put(4500, "巩固和扩展四级核心词汇，注重词组搭配和语境应用。通过四级真题和专业材料提升理解能力，强化学术写作技巧。每天积累10个地道表达，每周完成2-3套真题训练。");

        // 3500-4499：大学四级入门
        learningBuckets.put(3500, "系统学习四级词汇，强化词组和固定搭配。结合四级真题训练听力理解和快速阅读能力，培养基础学术写作能力。每天记忆20个新词，每周完成1-2套真题练习。");

        // 3000-3499：高中高级
        learningBuckets.put(3000, "夯实高考核心词汇，突破重点词组和固定搭配。通过高考真题和模拟试题提升解题能力，强化写作模板应用。坚持每日听说训练，定期完成模拟测试。");

        // 2000-2999：高中中级
        learningBuckets.put(2000, "系统积累高考词汇，强化词组应用和句型变换。专项突破弱项题型，提升阅读和写作能力。建立错题本，每周完成2套专项训练。");

        // 1600-1999：高中初级
        learningBuckets.put(1600, "建立高中基础词汇框架，掌握重点语法规则。培养题型解题思维，规范写作格式。坚持听说读写训练，做好笔记整理。");

        // 1300-1599：初中高级
        learningBuckets.put(1300, "巩固中考词汇，突破重点语法难点。通过真题训练提升解题技巧，规范写作结构。每天完成听说练习，积累常见错题。");

        // 1000-1299：初中中级
        learningBuckets.put(1000, "夯实初中词汇和语法体系，强化应用能力。通过专项练习提升理解能力，培养写作思维。每天坚持单词听写和课文朗读。");

        // 700-999：初中初级
        learningBuckets.put(700, "系统学习初中基础词汇，掌握基本语法规则。培养良好的学习习惯，加强听说训练。每天完成基础题型练习，做好重点笔记。");

        // 300-699：小学高级
        learningBuckets.put(500, "巩固小学高年级词汇，突破基础语法点。通过多样化练习提升理解能力，培养学习兴趣。坚持每日听读训练。");

        // 200-299：小学中级
        learningBuckets.put(300, "掌握小学中年级词汇，培养语音语感。通过趣味活动巩固知识，建立学习信心。完成基础听说读写练习。");

        // 50-199：小学初级
        learningBuckets.put(50, "学习小学低年级基础词汇，强化拼读规则。通过游戏活动提升学习兴趣，培养良好习惯。坚持每日跟读练习。");

        // 0-49：学龄前
        learningBuckets.put(0, "从字母和基础词汇开始，通过歌谣和游戏培养兴趣。建立语音意识，培养英语学习的好奇心。每天进行15分钟趣味学习活动。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        // 按照新标准设置等级
        gradeLevels.put(5500, "大学四级+水平");
        gradeLevels.put(4500, "大学四级进阶水平");
        gradeLevels.put(3500, "大学四级入门水平");
        gradeLevels.put(3000, "高中高级水平");
        gradeLevels.put(2000, "高中中级水平");
        gradeLevels.put(1600, "高中初级水平");
        gradeLevels.put(1300, "初中高级水平");
        gradeLevels.put(1000, "初中中级水平");
        gradeLevels.put(700, "初中初级水平");
        gradeLevels.put(500, "小学高级水平");
        gradeLevels.put(300, "小学中级水平");
        gradeLevels.put(50, "小学初级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}