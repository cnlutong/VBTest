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

//    public int correctVocabEstimate(int estimatedVocab) {
//        // 目标最大分数
//        double MAX_SCORE = 5200.0;
//        // 词库最大词汇量
//        double MAX_VOCAB = 7000.0;
//
//        // 将输入分数映射到0-5000范围
//        double mappedScore = (estimatedVocab / MAX_VOCAB) * MAX_SCORE;
//
//        // 随机波动范围为±1.5%
//        double randomFactorPercentage = 0.015;
//        // 生成-1.5%到+1.5%之间的随机因子
//        double randomFactor = (Math.random() * 2 * randomFactorPercentage) - randomFactorPercentage;
//        // 应用随机因子计算最终分数
//        double finalScore = mappedScore * (1 + randomFactor);
//
//        // 确保分数在0到5000之间
//        return (int) Math.min(Math.max(0, finalScore), MAX_SCORE);
//    }


//    public int correctVocabEstimate(int estimatedVocab) {
//        // 常量定义
//        double MAX_SCORE = 3150.0; // 修正后的最大词汇量
//        double MAX_VOCAB = 4580.0; // 词库最大词汇量
//        double reductionPercentage = 0.20; // 增加减少比例，使得分数更容易降低
//        double randomFactorPercentage = 0.015; // 增加随机因子，使分数波动更明显
//
//        // 计算调整映射的指数
//        double p = 0.50; // 降低中点参考值，使得整体曲线更倾向于产生较低的分数
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


    private String getResultInterpretation(int estimatedVocab) {
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        vocabLevels.put(4500, "经评估，您的词汇量达到大学四级水平，能够应对日常英语交流和基础学术场景。建议巩固核心词汇的用法，特别是高频短语和固定搭配，通过四级真题提高听力和写作能力。");

        vocabLevels.put(5500, "经评估，您的词汇量超越大学四级水平（四级+），具备更强的阅读和表达能力。建议拓展学术类词汇储备，进一步提升语言逻辑和表达多样性，为大学六级或更高考试做准备。");

        vocabLevels.put(3500, "经评估，您的词汇量达到高三水平，符合高考要求。具备理解复杂阅读材料和应对高考题型的能力。建议强化高频词组的应用，提高阅读和写作中的词汇灵活性，并通过真题训练提升准确性。");

        vocabLevels.put(3000, "经评估，您的词汇量达到高二水平，已掌握核心词汇，能够理解复杂文章，满足大多数高考题型的需求。建议扩充高考词汇，注重词组积累，并通过模拟练习提升实际运用能力。");

        vocabLevels.put(2000, "经评估，您的词汇量达到高一年级水平，具备高中学习的基础词汇储备，能理解基础文章内容。建议系统扩充词汇量，注重词组积累，为高考做好准备。");

//        初中
        vocabLevels.put(1600, "经评估，您的词汇量达到初三水平，符合中考要求，能理解初中课本和基础材料。建议加强中考常考词组和短语的积累，通过真题演练提升应试能力。");

        vocabLevels.put(1300, "经评估，您的词汇量达到初二水平，掌握较丰富的基础词汇，能理解课本内容和简单英语材料。建议积累中考词汇，注重重点词组和表达，通过练习逐步提高应用能力。");

        vocabLevels.put(1000, "经评估，您的词汇量达到初一水平，具备初中英语学习的基础，能理解简单内容。建议系统扩充词汇，积累基础短语，逐步提升实际应用能力。");

//        小学
        vocabLevels.put(700, "经评估，您的词汇量达到小学六年级水平，掌握核心词汇，能理解基础课文和日常用语。建议巩固小学词汇，提升应用能力，为初中学习做好准备。");

        vocabLevels.put(500, "经评估，您的词汇量达到小学五年级水平，积累了一定基础词汇，能理解课本内容和简单表达。建议扩充基础词汇，注重实际运用，为小升初打下基础。");

        vocabLevels.put(200, "经评估，您的词汇量达到小学四年级水平，能理解简单课文和基础用语。建议继续积累词汇，培养良好的英语学习习惯。");

        vocabLevels.put(100, "经评估，您的词汇量达到小学三年级水平，掌握一些基础单词和表达。建议通过多样化学习方式继续积累，培养英语学习兴趣。");

        vocabLevels.put(70, "经评估，您的词汇量达到小学二年级水平，处于词汇积累阶段，能认读简单单词和短语。建议通过有趣的学习活动逐步扩充词汇，养成良好的学习习惯。");

        vocabLevels.put(50, "经评估，您的词汇量达到小学一年级水平，正处于启蒙阶段。建议通过丰富的学习活动积累基础词汇，培养对英语的兴趣。");

        vocabLevels.put(0, "经评估，您正处于英语学习起点阶段，建议从字母和基础词汇开始，逐步积累并培养学习兴趣。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }


    // 返回学习建议

    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        learningBuckets.put(4500, "完善四级词汇体系，重点复习核心高频词汇及短语。结合真题和专项训练，提高听力和阅读理解能力。注重写作中的句型多样性，积累模板和表达技巧。每天坚持听力练习和口语输出，定期完成模拟题并分析薄弱环节。");
        learningBuckets.put(5500, "巩固和扩展四级+词汇，注重学术性和难度较高词汇的掌握。通过六级真题或高难度阅读材料提升理解能力，加强听力语速适应性训练。强化写作逻辑性和高级句型表达，积累例句和经典短文。每周完成总结性学习，识别并攻克易错点，为六级或其他高级考试奠定基础。");

                // 高中阶段 - 以高考备考为核心
        learningBuckets.put(3500, "制定系统的备考计划，注重真题和模拟题练习。建立词汇体系，强化完形填空、阅读理解等重点题型训练。每日听说训练，定期写作，积累高分句型。每周完成模拟题并分析错题，整理同义词、固定搭配等。");

        learningBuckets.put(3000, "复习高考词汇，强化重点词组与搭配应用。进行专题训练，完成近年真题练习，提升解题技巧。定期听力训练，注重口语表达。规范写作，积累亮点句型，定期测试查漏补缺。");

        learningBuckets.put(2000, "梳理高中词汇和语法，重视词组搭配和句型积累。基础题型训练，培养解题思维。每日听力，定期口语练习，强化语法专项。安排课内外学习时间，建立错题本和重点笔记。");

        // 初中阶段 - 以中考达标为目标
        learningBuckets.put(1600, "复习中考词汇与短语，通过真题和专项训练提升解题技巧。强化听说训练，规范写作，积累常用句型。制定复习计划，定期测试，建立知识框架和错题记录。");

        learningBuckets.put(1300, "巩固初中重点词汇和语法，注重听说读写的全面培养。结合单元测试查漏补缺，整理错题本，培养答题习惯和学习兴趣。定期朗读听力，提升表达能力。");

        learningBuckets.put(1000, "系统学习初中基础词汇和语法，积累课文重点句型。课内外结合训练，提高学习兴趣。定期听力训练，坚持朗读，合理安排学习时间，做好笔记整理。");

        // 小学高年级 - 以升学准备为重点
        learningBuckets.put(700, "复习小升初词汇与语法，强化重点题型。全面培养听说读写能力，做好课堂笔记，积累重点短语和句型。每日听说训练，通过朗读提升语音，为升学做好准备。");

        learningBuckets.put(500, "掌握五年级词汇和句型，注重语音语调训练。定期单元测试查漏补缺，养成预习复习习惯，提升口语表达能力。通过有趣的学习活动激发学习兴趣。");

        learningBuckets.put(200, "掌握四年级词汇和句型，通过课堂活动和练习提升兴趣和效果。培养听说能力，定期测试巩固信心，坚持每日听写和朗读。");

        learningBuckets.put(100, "学习三年级词汇和句型，重视发音训练，激发兴趣。定期朗读和听写练习，通过游戏和歌曲增加学习趣味性。");

        learningBuckets.put(70, "学习二年级词汇，强化拼读规则，培养语音基础。通过课堂活动提高积极性，坚持听写和朗读，培养英语语感。");

        learningBuckets.put(50, "学习一年级基础词汇，注重拼读训练，通过趣味活动激发学习兴趣。坚持听写和朗读，培养语感和自信。");

        learningBuckets.put(0, "从字母和基础词汇开始，通过游戏和儿歌培养兴趣和语感。养成良好的学习习惯，完成基础练习。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(4500, "大学四级水平");
        gradeLevels.put(5500, "大学四级+水平");

        gradeLevels.put(3500, "高三水平");
        gradeLevels.put(3000, "高二水平");
        gradeLevels.put(2000, "高一水平");
        gradeLevels.put(1600, "初三水平");
        gradeLevels.put(1300, "初二水平");
        gradeLevels.put(1000, "初一水平");
        gradeLevels.put(700, "小学六年级水平");
        gradeLevels.put(500, "小学五年级水平");
        gradeLevels.put(200, "小学四年级水平");
        gradeLevels.put(100, "小学三年级水平");
        gradeLevels.put(70, "小学二年级水平");
        gradeLevels.put(50, "小学一年级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}