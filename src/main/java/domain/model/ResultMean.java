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

        // 大学四级+水平 (5500+)
        vocabLevels.put(5500, "经评估，您的词汇量达到大学四级+水平，具备较强的学术阅读和专业交流能力。您能理解较为深入的学术材料，并能用英语进行专业领域的交流。建议继续拓展专业词汇，提升学术写作和表达能力。");

        // 大学四级水平 (4500-5499)
        vocabLevels.put(4500, "经评估，您的词汇量达到大学四级水平，能够适应大学英语学习和基础学术场景。您具备理解英语原版材料和进行日常交流的能力。建议继续扩充学术词汇，强化听说读写的综合应用。");

        // 高中高级水平 (3500-4499)
        vocabLevels.put(3500, "经评估，您的词汇量达到高中高级水平，掌握了丰富的高考词汇。您能理解较复杂的文章，具备应对各类高考题型的能力。建议重点提升词汇应用的准确性和灵活性。");

        // 高中中级水平 (3000-3499)
        vocabLevels.put(3000, "经评估，您的词汇量达到高中中级水平，已建立较扎实的高中词汇体系。您能理解标准考试材料和一般性文章。建议继续扩充高考词汇，强化词组和固定搭配的积累。");

        // 高中初级水平 (2000-2999)
        vocabLevels.put(2000, "经评估，您的词汇量达到高中初级水平，具备基础的高中学习词汇储备。您能理解基础性文章和日常表达。建议系统性扩充高中词汇，注重词组和句型积累。");

        // 初中高级水平 (1600-1999)
        vocabLevels.put(1600, "经评估，您的词汇量达到初中高级水平，具备较好的中考词汇基础。您能理解初中课本和基础英语材料。建议着重积累中考重点词组，提升实际应用能力。");

        // 初中中级水平 (1300-1599)
        vocabLevels.put(1300, "经评估，您的词汇量达到初中中级水平，已掌握主要的初中基础词汇。您能理解日常表达和简单文章。建议继续积累中考词汇，加强词汇实际运用能力。");

        // 初中初级水平 (1000-1299)
        vocabLevels.put(1000, "经评估，您的词汇量达到初中初级水平，具备基础的初中学习能力。您能理解简单的课文内容。建议系统性扩充初中词汇，培养良好的学习习惯。");

        // 小学高级水平 (700-999)
        vocabLevels.put(700, "经评估，您的词汇量达到小学高级水平，已具备小升初的词汇基础。您能理解基础课文和日常用语。建议巩固小学重点词汇，为初中学习打好基础。");

        // 小学中级水平 (300-699)
        vocabLevels.put(300, "经评估，您的词汇量达到小学中级水平，掌握了基本的日常词汇。您能理解简单的课本内容。建议通过多样化学习继续积累词汇，培养学习兴趣。");

        // 小学初级水平 (200-299)
        vocabLevels.put(200, "经评估，您的词汇量达到小学初级水平，具备基础词汇储备。您能认读简单的单词和短语。建议通过趣味性活动扩充词汇量，养成良好的学习习惯。");

        // 学龄前水平 (0-199)
        vocabLevels.put(0, "经评估，您正处于英语学习的起步阶段。建议从字母和基础词汇开始学习，通过生动有趣的方式培养学习兴趣和语感。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }


    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // 大学四级+水平 (5500+)
        learningBuckets.put(5500, "重点拓展学术词汇量，强化专业领域用语和地道表达。通过原版文献训练深度阅读理解，进行高难度听力训练提升语速适应性。注重学术写作范式，培养批判性思维能力。建议每周完成3-4篇学术文章精读和听力训练。");

        // 大学四级水平 (4500-5499)
        learningBuckets.put(4500, "巩固四级核心词汇体系，专注词语搭配应用和句型变换。突破听说瓶颈，强化写作逻辑性和连贯性。系统化整理易错点，通过真题训练提升应试技巧。建议每天积累10个地道表达，每周完成2-3套真题训练。");

        // 高中高级水平 (3500-4499)
        learningBuckets.put(3500, "系统复习高考词汇，突破近义词辨析和词义延伸。强化完形与阅读理解技巧，提升写作多样性和准确度。保持高强度听说训练，每天完成1篇范文背诵和30分钟口语练习。定期做题归纳解题思路。");

        // 高中中级水平 (3000-3499)
        learningBuckets.put(3000, "夯实高中词汇基础，掌握固定搭配和常用句型。专项突破弱项题型，通过范文积累提升写作水平。每日坚持听说读写训练，培养英语思维习惯。建议每周完成2套专项训练和1次模拟测试。");

        // 高中初级水平 (2000-2999)
        learningBuckets.put(2000, "建立高中基础词汇框架，强化语法体系和应用能力。培养题型解题思维，制定系统学习规划。保持听说练习频率，重视课堂参与。建议每天记忆20个新词，完成1篇短文写作练习。");

        // 初中高级水平 (1600-1999)
        learningBuckets.put(1600, "巩固初中核心词汇，突破重点语法难点。通过真题训练提升解题能力，规范写作模式和思路。坚持听说训练，培养语言应用能力。每周完成2次单元测试，积累常见错题。");

        // 初中中级水平 (1300-1599)
        learningBuckets.put(1300, "稳固初中词汇语法体系，强化实际应用能力。突破听说读写难点，培养学习兴趣和良好习惯。通过多样化练习提升理解能力。建议每天进行30分钟口语练习，完成1篇短文改写。");

        // 初中初级水平 (1000-1299)
        learningBuckets.put(1000, "构建初中基础词汇体系，掌握基本语法规则。培养良好学习习惯，加强听说训练频率。保持学习积极性，做好课堂笔记。每天完成单词听写和课文朗读，培养语感。");

        // 小学高级水平 (700-999)
        learningBuckets.put(700, "巩固小升初重点词汇，突破基础语法难点。强化听说读写能力，为升学做系统性准备。培养学习兴趣和自信心。建议通过游戏和互动方式，每天坚持词汇听写和课文朗读。");

        // 小学中级水平 (300-699)
        learningBuckets.put(300, "掌握小学高年级词汇，培养准确语音语感。通过趣味活动巩固知识，建立学习信心和成就感。坚持每日听读训练，培养英语学习兴趣。完成基础题型练习，做好重点笔记。");

        // 小学初级水平 (200-299)
        learningBuckets.put(200, "学习小学基础词汇，强化拼读规则和语音基础。通过游戏活动提升学习兴趣，培养良好学习习惯。每天完成词汇卡片记忆和课文跟读，参与课堂互动活动。");

        // 学龄前水平 (0-199)
        learningBuckets.put(0, "从字母和基础词汇起步，通过互动游戏培养学习兴趣。建立语音意识，为系统学习打好基础。每天进行15分钟字母歌谣学习和简单对话练习，享受英语学习乐趣。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(5500, "大学四级+水平");
        gradeLevels.put(4500, "大学四级水平");
        gradeLevels.put(3500, "高中高级水平");
        gradeLevels.put(3000, "高中中级水平");
        gradeLevels.put(2000, "高中初级水平");
        gradeLevels.put(1600, "初中高级水平");
        gradeLevels.put(1300, "初中中级水平");
        gradeLevels.put(1000, "初中初级水平");
        gradeLevels.put(700, "小学高级水平");
        gradeLevels.put(300, "小学中级水平");
        gradeLevels.put(200, "小学初级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}