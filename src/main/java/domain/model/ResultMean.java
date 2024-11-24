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

//    // 词汇量估计值修正
//    public int correctVocabEstimate(int estimatedVocab) {
//        // 常量定义
//        double MAX_SCORE = 3500.0; // 最大分数
//        double MAX_VOCAB = 5800.0; // 最大词汇量
//        double reductionPercentage = 0.12; // 减少比例
//        double randomFactorPercentage = 0.015; // 随机因子
//
//        // 计算调整映射的指数
//        double p = 0.50; // 中点参考值
//        double logOneMinusReduction = Math.log(1 - reductionPercentage);
//        double logP = Math.log(p);
//        double k = 1 + logOneMinusReduction / logP;
//
//        // 调整后的映射函数
//        double baseScore = MAX_SCORE * Math.pow(estimatedVocab / MAX_VOCAB, k);
//
//        // 应用随机因子
//        double randomFactor = (Math.random() * 2 * randomFactorPercentage) - randomFactorPercentage;
//        double finalScore = baseScore * (1 + randomFactor);
//
//        // 确保最终分数在0到MAX_SCORE之间
//        finalScore = Math.max(0, Math.min(MAX_SCORE, finalScore));
//
//        return (int) finalScore;
//    }

    public int correctVocabEstimate(int estimatedVocab) {
        // 常量定义
        double MAX_SCORE = 3150.0; // 修正后的最大词汇量
        double MAX_VOCAB = 5800.0; // 词库最大词汇量
        double reductionPercentage = 0.20; // 增加减少比例，使得分数更容易降低
        double randomFactorPercentage = 0.015; // 增加随机因子，使分数波动更明显

        // 计算调整映射的指数
        double p = 0.50; // 降低中点参考值，使得整体曲线更倾向于产生较低的分数
        double logOneMinusReduction = Math.log(1 - reductionPercentage);
        double logP = Math.log(p);
        double k = 1 + logOneMinusReduction / logP;

        // 调整后的映射函数
        double baseScore = MAX_SCORE * Math.pow(estimatedVocab / MAX_VOCAB, k);

        // 应用随机因子
        double randomFactor = (Math.random() * 2 * randomFactorPercentage) - randomFactorPercentage;
        double finalScore = baseScore * (1 + randomFactor);

        // 确保最终分数在0到MAX_SCORE之间
        finalScore = Math.max(0, Math.min(MAX_SCORE, finalScore));

        return (int) finalScore;
    }


    private String getResultInterpretation(int estimatedVocab) {
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        vocabLevels.put(3500, "经评估，您的词汇量达到高三水平，符合高考要求。具备理解复杂阅读材料和应对高考题型的能力。建议强化高频词组的应用，提高阅读和写作中的词汇灵活性，并通过真题训练提升准确性。");

        vocabLevels.put(2800, "经评估，您的词汇量达到高二水平，已掌握核心词汇，能够理解复杂文章，满足大多数高考题型的需求。建议扩充高考词汇，注重词组积累，并通过模拟练习提升实际运用能力。");

        vocabLevels.put(2400, "经评估，您的词汇量达到高一年级水平，具备高中学习的基础词汇储备，能理解基础文章内容。建议系统扩充词汇量，注重词组积累，为高考做好准备。");

        vocabLevels.put(1800, "经评估，您的词汇量达到初三水平，符合中考要求，能理解初中课本和基础材料。建议加强中考常考词组和短语的积累，通过真题演练提升应试能力。");

        vocabLevels.put(1400, "经评估，您的词汇量达到初二水平，掌握较丰富的基础词汇，能理解课本内容和简单英语材料。建议积累中考词汇，注重重点词组和表达，通过练习逐步提高应用能力。");

        vocabLevels.put(1000, "经评估，您的词汇量达到初一水平，具备初中英语学习的基础，能理解简单内容。建议系统扩充词汇，积累基础短语，逐步提升实际应用能力。");

        vocabLevels.put(800, "经评估，您的词汇量达到小学六年级水平，掌握核心词汇，能理解基础课文和日常用语。建议巩固小学词汇，提升应用能力，为初中学习做好准备。");

        vocabLevels.put(650, "经评估，您的词汇量达到小学五年级水平，积累了一定基础词汇，能理解课本内容和简单表达。建议扩充基础词汇，注重实际运用，为小升初打下基础。");

        vocabLevels.put(450, "经评估，您的词汇量达到小学四年级水平，能理解简单课文和基础用语。建议继续积累词汇，培养良好的英语学习习惯。");

        vocabLevels.put(350, "经评估，您的词汇量达到小学三年级水平，掌握一些基础单词和表达。建议通过多样化学习方式继续积累，培养英语学习兴趣。");

        vocabLevels.put(150, "经评估，您的词汇量达到小学二年级水平，处于词汇积累阶段，能认读简单单词和短语。建议通过有趣的学习活动逐步扩充词汇，养成良好的学习习惯。");

        vocabLevels.put(50, "经评估，您的词汇量达到小学一年级水平，正处于启蒙阶段。建议通过丰富的学习活动积累基础词汇，培养对英语的兴趣。");

        vocabLevels.put(0, "经评估，您正处于英语学习起点阶段，建议从字母和基础词汇开始，逐步积累并培养学习兴趣。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }


    // 返回学习建议

    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // 高中阶段 - 以高考备考为核心
        learningBuckets.put(3500, "制定系统的备考计划，注重真题和模拟题练习。建立词汇体系，强化完形填空、阅读理解等重点题型训练。每日听说训练，定期写作，积累高分句型。每周完成模拟题并分析错题，整理同义词、固定搭配等。");

        learningBuckets.put(2800, "复习高考词汇，强化重点词组与搭配应用。进行专题训练，完成近年真题练习，提升解题技巧。定期听力训练，注重口语表达。规范写作，积累亮点句型，定期测试查漏补缺。");

        learningBuckets.put(2400, "梳理高中词汇和语法，重视词组搭配和句型积累。基础题型训练，培养解题思维。每日听力，定期口语练习，强化语法专项。安排课内外学习时间，建立错题本和重点笔记。");

        // 初中阶段 - 以中考达标为目标
        learningBuckets.put(1800, "复习中考词汇与短语，通过真题和专项训练提升解题技巧。强化听说训练，规范写作，积累常用句型。制定复习计划，定期测试，建立知识框架和错题记录。");

        learningBuckets.put(1400, "巩固初中重点词汇和语法，注重听说读写的全面培养。结合单元测试查漏补缺，整理错题本，培养答题习惯和学习兴趣。定期朗读听力，提升表达能力。");

        learningBuckets.put(1000, "系统学习初中基础词汇和语法，积累课文重点句型。课内外结合训练，提高学习兴趣。定期听力训练，坚持朗读，合理安排学习时间，做好笔记整理。");

        // 小学高年级 - 以升学准备为重点
        learningBuckets.put(800, "复习小升初词汇与语法，强化重点题型。全面培养听说读写能力，做好课堂笔记，积累重点短语和句型。每日听说训练，通过朗读提升语音，为升学做好准备。");

        learningBuckets.put(650, "掌握五年级词汇和句型，注重语音语调训练。定期单元测试查漏补缺，养成预习复习习惯，提升口语表达能力。通过有趣的学习活动激发学习兴趣。");

        // 小学中低年级 - 以兴趣培养为主
        learningBuckets.put(450, "掌握四年级词汇和句型，通过课堂活动和练习提升兴趣和效果。培养听说能力，定期测试巩固信心，坚持每日听写和朗读。");

        learningBuckets.put(350, "学习三年级词汇和句型，重视发音训练，激发兴趣。定期朗读和听写练习，通过游戏和歌曲增加学习趣味性。");

        learningBuckets.put(150, "学习二年级词汇，强化拼读规则，培养语音基础。通过课堂活动提高积极性，坚持听写和朗读，培养英语语感。");

        learningBuckets.put(50, "学习一年级基础词汇，注重拼读训练，通过趣味活动激发学习兴趣。坚持听写和朗读，培养语感和自信。");

        learningBuckets.put(0, "从字母和基础词汇开始，通过游戏和儿歌培养兴趣和语感。养成良好的学习习惯，完成基础练习。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(3500, "高三水平");
        gradeLevels.put(2800, "高二水平");
        gradeLevels.put(2400, "高一水平");
        gradeLevels.put(1800, "初三水平");
        gradeLevels.put(1400, "初二水平");
        gradeLevels.put(1000, "初一水平");
        gradeLevels.put(800, "小学六年级水平");
        gradeLevels.put(650, "小学五年级水平");
        gradeLevels.put(450, "小学四年级水平");
        gradeLevels.put(350, "小学三年级水平");
        gradeLevels.put(150, "小学二年级水平");
        gradeLevels.put(50, "小学一年级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}