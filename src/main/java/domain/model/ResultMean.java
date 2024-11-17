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
        double MAX_SCORE = 3150.0; //
        double MAX_VOCAB = 5800.0; // 最大词汇量保持不变
        double reductionPercentage = 0.15; // 略微增加减少比例，使得分数更容易降低
        double randomFactorPercentage = 0.02; // 略微增加随机因子，使分数波动更明显

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

        // 高中阶段评估
        vocabLevels.put(3500, "经评估，您目前的词汇量已达到高三年级水平，基本符合高考考纲要求。从评测数据来看，您在词汇认知和运用方面具备扎实的基础，能够较好地应对高考各类题型的词汇要求。词汇量足以支撑您理解较复杂的阅读材料，完成高考完形填空，并能就大部分话题进行书面和口头表达。建议您在此基础上，重点关注高频词组的深度应用，包括词义辨析、同义替换、语境运用等方面，着重提高在阅读理解和写作中的词汇灵活运用能力。同时建议您通过真题训练，进一步提升词汇应用的准确性和得分能力。");

        vocabLevels.put(2800, "经评估，您目前的词汇量达到高二年级水平，处于高考备考的关键阶段。测评数据显示，您已经掌握了大部分高中阶段的核心词汇，具备理解较为复杂的英语文章的能力，能够应对大多数高考题型的基本要求。您现阶段的词汇量可以支撑基础的阅读理解和写作任务，在听说方面也具备一定的交际能力。建议您在此基础上，系统性地扩充高考词汇量，特别注重词组搭配和固定表达的积累，为冲刺高考打下坚实的基础。同时要通过模拟训练，提升词汇的实际运用能力。");

        vocabLevels.put(2400, "经评估，您目前的词汇量达到高一年级水平，正处于高中英语学习的起步阶段。测评结果表明，您已经具备了高中英语学习的基础词汇储备，能够理解课本中的基础内容和简单的英语文章。您的词汇量可以支撑基本的日常交际和课堂学习需求，但在深度理解和灵活运用方面还需要进一步提升。建议您借助课本和教学资源，系统性地扩充词汇量，注重词组和短语的积累，为今后的高考备考打好基础。同时要通过多样化的练习，逐步提高词汇的实际应用能力。");

        // 初中阶段评估
        vocabLevels.put(1500, "经评估，您目前的词汇量达到初三年级水平，基本符合中考大纲要求。测评数据显示，您在词汇方面已经建立起较为扎实的基础，能够较好地理解初中课本内容和基础的英语材料。您的词汇储备足以应对大多数中考题型，在日常交际中也能进行基本的表达。建议您在现有基础上，重点关注中考常考词组和重点短语的积累，通过系统训练提高词汇运用的准确性。同时要注意通过中考真题演练，提升实际应试能力。");

        vocabLevels.put(1200, "经评估，您目前的词汇量达到初二年级水平，处于初中英语学习的重要过渡期。测评结果显示，您已经掌握了较为丰富的基础词汇，能够理解大部分课本内容和基础英语材料。您的词汇量可以支撑基本的课堂学习和简单的日常交际需求。建议您系统性地积累中考词汇，特别注重重点词组和常用表达的掌握，同时要通过多样化的练习，逐步提高词汇的实际应用能力，为冲刺中考打好基础。");

        vocabLevels.put(1000, "经评估，您目前的词汇量达到初一年级水平，正处于初中英语学习的起步阶段。测评数据表明，您已经具备了初中英语学习所需的基础词汇储备，能够理解简单的课本内容和基础的英语表达。您的词汇量可以满足基本的课堂学习需求，但在实际运用方面还需要进一步加强。建议您通过系统的学习，逐步扩充词汇量，注重基础词组和句型的积累，为今后的初中英语学习打好基础。");

        // 小学高年级评估
        vocabLevels.put(800, "经评估，您目前的词汇量达到小学六年级水平，即将进入初中学习阶段。测评结果显示，您已经掌握了小学阶段的核心词汇，能够理解基础的英语课文内容和简单的日常用语。您的词汇储备为进入初中阶段打下了良好的基础。建议您在此基础上，系统巩固小学阶段的重点词汇，通过多样化的练习提高词汇应用能力，为顺利过渡到初中英语学习做好充分准备。");

        vocabLevels.put(650, "经评估，您目前的词汇量达到小学五年级水平，正处于小学英语学习的关键阶段。测评数据表明，您已经积累了一定数量的基础词汇，能够理解课本中的基本内容和简单的英语表达。您的词汇量可以支撑基础的课堂学习和简单的日常交际。建议您继续通过系统学习，扩充基础词汇量，注重词语的实际运用，为小升初考试打好基础。");

        // 小学中低年级评估
        vocabLevels.put(450, "经评估，您目前的词汇量达到小学四年级水平，处于小学英语学习的成长阶段。测评结果显示，您已经掌握了一定数量的基础词汇，能够理解简单的课文内容和基本的课堂用语。建议您继续通过课堂学习和课后练习，稳步提升词汇量，培养良好的英语学习习惯，为今后的英语学习奠定良好基础。");

        vocabLevels.put(350, "经评估，您目前的词汇量达到小学三年级水平，正处于基础词汇积累阶段。测评数据表明，您已经能够认识并运用一些基本的英语单词，掌握简单的课堂用语和日常表达。建议您通过多样化的学习方式，继续积累基础词汇，培养英语学习兴趣，为今后的学习打好基础。");

        vocabLevels.put(150, "经评估，您目前的词汇量达到小学二年级水平，处于英语学习的起步阶段。测评结果显示，您已经开始积累基本的英语词汇，能够认读简单的单词和短语。建议您通过生动有趣的学习活动，继续积累基础词汇，培养良好的学习习惯，为今后的英语学习打好基础。");

        vocabLevels.put(50, "经评估，您目前的词汇量达到小学一年级水平，正处于英语学习的启蒙阶段。测评数据显示，您已经开始接触基本的英语字母和简单词汇。建议您通过丰富多样的学习活动，循序渐进地积累基础词汇，培养对英语的学习兴趣，为今后的学习奠定良好基础。");

        vocabLevels.put(0, "经评估，您目前正处于英语学习的起点阶段。测评结果显示，您即将开启英语学习之旅。建议您从字母认读和基本词汇开始，通过生动有趣的学习活动培养学习兴趣，循序渐进地开展英语学习。这是每位学习者的必经阶段，通过系统的学习和练习，您的词汇量将会稳步提升。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }



    // 返回学习建议

    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // 高中阶段 - 以高考备考为核心
        learningBuckets.put(3500, "建议按照高考大纲要求制定系统的备考计划，以真题和权威模拟题为导向，建立完整的词根词缀与同义词体系，着重突破考纲词汇运用。针对性强化完形填空、阅读理解等重点题型，尤其注意提升解题速度与准确度。每日坚持听说训练，积累地道口语表达；定期进行限时写作练习，储备高分作文素材和经典句型。建议每周完成2-3套标准模拟题，通过错题分析总结易错点和解题技巧，重视各题型间的得分能力平衡。同时建立个人词汇笔记本，系统整理同义词组、固定搭配及其典型例句。");

        learningBuckets.put(2800, "建议系统复习高考必修与选修词汇，强化重点词组与固定搭配应用能力，建立完整的知识体系。进行针对性的专题训练，以提升对各类题型的解题能力和应试技巧。重点做好近三年高考真题与经典模拟题的练习，注重总结答题方法与解题思路。定期进行听力专项训练，重视口语表达的地道性；规范完成写作练习，积累高分素材与亮点句型。建议制定个性化的复习计划，定期进行单元测试和查漏补缺，培养良好的英语学习习惯。对于重点语法项目，要注意结合例句反复练习，提高知识运用能力。");

        learningBuckets.put(2400, "建议全面梳理高中基础词汇与核心语法知识点，系统建立词汇体系，注重词组搭配与重难点句型积累。配合教材内容进行基础题型训练，培养良好的解题思维与答题习惯。每天坚持听力训练，提升语音语感；定期进行口语练习，增强英语表达能力。针对性复习重点语法项目，通过专项练习强化知识运用。建议合理规划课内外学习时间，建立错题本和重点笔记，定期进行测试评估，及时发现并解决学习中的问题。同时要培养英语阅读习惯，为高考复习打下坚实的知识基础。");

        // 初中阶段 - 以中考达标为目标
        learningBuckets.put(1500, "建议系统复习中考大纲词汇与重点短语，通过专项训练掌握各类题型的解题技巧和方法。每天坚持做中考真题训练，重点突破完形填空与阅读理解，注意提升答题速度与准确率。针对性强化听说训练，培养英语语感；规范书面表达，积累常用句型和地道表达。建议制定详细的复习计划，合理安排各题型练习时间，定期进行阶段性测试。要善于总结易错点和重点考点，建立系统的知识框架，培养良好的学习方法和解题思维。同时注重课堂笔记整理，做好重点知识归纳，为冲刺中考打下扎实基础。");

        learningBuckets.put(1200, "建议强化初中教材重点词汇与语法项目学习，系统掌握各单元重点知识点。通过课内外结合的方式，巩固基础知识，同时培养英语学习兴趣。注重听说读写能力的全面培养，定期进行单元测试和查漏补缺。针对性练习重点题型，培养良好的解题思维和答题习惯。建议做好课堂笔记，建立个人错题本，系统整理易错点和重点语法项目。每天坚持英语听力训练，通过朗读积累地道表达，为顺利完成初中英语学习打好基础。同时要培养英语学习习惯，为将来的中考复习做好充分准备。");

        learningBuckets.put(1000, "建议系统学习初中基础词汇和语法知识，注重课文重点句型的积累和运用。认真完成课内练习，培养良好的学习习惯和答题思维。通过多样化的学习方式，提高英语学习的趣味性和效率。定期进行听力训练，培养英语语感；坚持课文朗读，提升口语表达能力。建议合理安排课内外学习时间，做好笔记整理和错题分析。针对性地进行基础题型训练，逐步接触中考试题，为今后的中考复习打下良好基础。同时要培养英语学习兴趣，建立学习自信。");

        // 小学高年级 - 以升学准备为重点
        learningBuckets.put(800, "建议系统复习小升初重点词汇与语法知识，通过多样化训练提升应试能力和英语综合水平。针对性强化重点题型训练，尤其是单项选择和完形填空题型。注重听说读写能力的全面培养，培养良好的英语学习习惯。定期进行课内外测试，及时发现并解决学习中的问题。建议做好课堂笔记整理，建立个人单词本，积累重点短语和句型。每天坚持英语听说训练，通过朗读和跟读提升语音准确度，为小升初考试做好充分准备。同时要注意培养英语学习兴趣，建立学习自信心。");

        learningBuckets.put(650, "建议扎实掌握五年级教材词汇与重点句型，通过丰富多样的练习形式巩固课堂所学内容。重视语音语调训练，培养良好的英语语感，加强听说技能的培养。定期进行单元测试，及时查漏补缺。建议养成课前预习和课后复习的好习惯，认真完成作业，做好重点知识整理。每天进行英语听说训练，坚持课文朗读，培养英语口语表达能力。通过生动有趣的学习活动，提高英语学习的积极性，为升学考试打下坚实基础。");

        // 小学中低年级 - 以兴趣培养为主
        learningBuckets.put(450, "建议认真掌握四年级教材词汇与基础句型，通过生动有趣的课堂活动和课后练习，提升英语学习兴趣和效果。注重语音规则训练，培养良好的听说能力，为今后的英语学习打好基础。定期进行课内测试，建立学习信心。建议做好课堂笔记，坚持每日单词听写，培养良好的学习习惯。通过丰富多样的英语学习活动，如英语歌曲、故事等，激发学习热情，提升语感。同时要注意培养自主学习能力，为今后的英语学习奠定良好基础。");

        learningBuckets.put(350, "建议系统学习三年级教材词汇和句型，重视音标与语音规则训练，培养良好的英语发音习惯。通过丰富的课堂活动和课后练习，激发学习兴趣，培养英语语感。定期完成课后作业，培养独立学习能力。建议每天进行课文朗读和单词听写练习，做好课堂笔记整理。通过英语游戏、歌曲等形式，增加学习趣味性，提高学习效果。养成良好的预习复习习惯，为今后的英语学习打好基础。");

        learningBuckets.put(150, "建议扎实掌握二年级教材词汇，强化字母拼读规则训练，培养良好的语音基础。通过生动有趣的课堂活动和课后练习，提高英语学习的积极性。认真完成听写和作业，培养良好的学习习惯。建议每天进行课文朗读，坚持单词拼写练习。通过互动游戏、英语儿歌等形式，激发学习兴趣，培养英语语感。注重课堂参与，增强学习自信心，为今后的英语学习奠定良好基础。");

        learningBuckets.put(50, "建议系统学习一年级基础词汇，注重字母认读与拼写训练，培养良好的语音基础。通过生动形象的教学活动，激发英语学习兴趣，培养基本的语感。认真完成课后练习，养成良好的学习习惯。建议每天进行字母认读和单词拼写练习，坚持课文朗读。通过英语儿歌、故事等形式，增加学习趣味性，建立学习自信。注重课堂参与，为今后的英语学习打好基础。");

        learningBuckets.put(0, "建议从字母认读和基础单词学习开始，通过图片识词、儿歌等形式，培养英语学习兴趣。重视课堂参与，培养良好的听说习惯。定期进行字母和单词的认读练习，巩固基础知识。建议通过生动有趣的游戏和活动，增强学习积极性，培养英语语感。注意培养良好的学习习惯，做好课堂笔记整理，完成基础练习。循序渐进地开展英语学习，为今后打下良好的基础。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(3500, "高三水平");
        gradeLevels.put(2800, "高二水平");
        gradeLevels.put(2400, "高一水平");
        gradeLevels.put(2000, "初三水平");
        gradeLevels.put(1500, "初二水平");
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