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

    // 词汇量估计值修正
    public int correctVocabEstimate(int estimatedVocab) {

        return Math.min(3500, (int) (estimatedVocab * (3500.0 / 5980.0)));
    }

    private String getResultInterpretation(int estimatedVocab) {
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        // 高中阶段
        vocabLevels.put(3500, """
        您的词汇量已达到高三水平，基本满足高考大纲要求。这个水平能够应对高考全部题型，包括阅读理解、完形填空等重点题型。
        您已具备较强的英语语言应用能力，可以通过真题训练进一步提高解题速度和准确度。建议重点关注高考高频词组和固定搭配，着重提升词汇的灵活运用能力。""");

        vocabLevels.put(2800, """
        您的词汇量达到高二水平，处于高考备考的关键阶段。这个水平已能理解较复杂的文章材料，具备处理大部分高考题型的能力。
        建议您通过系统做题来强化词汇运用，特别注意完形填空和阅读理解中的词义辨析，为冲刺高考打下坚实基础。""");

        vocabLevels.put(2400, """
        您的词汇量达到高一水平，已具备高中英语学习的基础能力。目前您能理解教材内容和基础题型，具备一定的英语语言运用能力。
        建议您开始系统性地积累高考词汇，通过课本练习和基础题强化重点语法，为今后的高考备考打好基础。""");

        // 初中阶段
        vocabLevels.put(1500, """
        您的词汇量达到初三水平，接近中考考纲要求。这个基础能够支撑您应对各类中考题型，特别是基础题和中等难度题目。
        建议您通过中考真题训练来查漏补缺，重点关注易错词组和重点语法，为冲刺中考做好充分准备。""");

        vocabLevels.put(1200, """
        您的词汇量达到初二水平，处于初中英语学习的重要过渡期。您现在能够理解较长的课文内容，具备一定的听说读写能力。
        建议您开始有计划地积累中考词汇，通过课内外练习强化基础知识，为进入初三冲刺阶段做好准备。""");

        vocabLevels.put(1000, """
        您的词汇量达到初一水平，已经具备了初中英语学习的基本能力。您能理解基础的课文内容，掌握简单的语法规则。
        建议您注重课堂学习，打牢词汇和语法基础，培养良好的学习习惯，为后续的初中英语学习奠定基础。""");

        // 小学高年级
        vocabLevels.put(800, """
        您的词汇量达到小学六年级水平，即将进入初中学习阶段。您已掌握小学阶段的核心词汇，能理解基础的英语表达。
        建议您着重巩固小学阶段的重点知识，为顺利过渡到初中英语学习做好准备。""");

        vocabLevels.put(650, """
        您的词汇量达到小学五年级水平，正处于小学英语学习的关键时期。您能够理解课本内容，具备基本的听说读写能力。
        建议您继续巩固词汇基础，加强课文朗读和听力训练，为小升初考试做好准备。""");

        // 小学中低年级
        vocabLevels.put(450, """
        您的词汇量达到小学四年级水平，已经掌握了基础的英语交际词汇。您能读懂简单的课文内容，进行基础的课堂交流。
        建议您保持学习热情，通过多听多读来巩固所学知识，为今后的英语学习打好基础。""");

        vocabLevels.put(350, """
        您的词汇量达到小学三年级水平，正处于英语基础培养阶段。您已能认读基本的英语单词，理解简单的课堂用语。
        建议您注重课堂参与，培养英语兴趣，通过课后练习来巩固学习效果。""");

        vocabLevels.put(150, """
        您的词汇量达到小学二年级水平，处于英语学习的起步阶段。您已掌握基本的字母拼读规则，认识一些简单词汇。
        建议您多参与课堂活动，培养英语学习兴趣，为今后的学习打好基础。""");

        vocabLevels.put(50, """
        您的词汇量达到小学一年级水平，正在开启英语学习之旅。您已经开始接触英语字母和基础单词的认读。
        建议您通过课堂活动和课后练习，培养对英语的兴趣，建立良好的学习习惯。""");

        vocabLevels.put(0, """
        您正处于英语学习的启蒙阶段，开始接触这门语言。这是每个学习者的必经阶段。
        建议您从字母和基础单词开始，通过有趣的学习活动来培养英语学习兴趣。""");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }



    // 返回学习建议

    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        // 高中阶段 - 以高考为导向
        learningBuckets.put(3500, """
        重点刷高考真题和权威模拟题，背诵大纲词汇和超纲词汇，特别关注易错词组和同义词替换。建议每天坚持背诵高频词组和固定搭配，做题时总结各题型答题技巧。
        完形填空重点关注逻辑关系和上下文理解，阅读理解注重速度和准确度训练，听力重点突破长对话和校园场景，写作积累地道表达和高分句型。""");

        learningBuckets.put(2800, """
        系统复习高考词汇表和常考短语，重点攻克教材重难点词组。针对性训练高考题型，重点做近年真题和经典模拟题。
        着重提升阅读速度和理解准确度，完形填空注意总结词语搭配规律，定期练习高考听力，积累写作素材和固定搭配。""");

        learningBuckets.put(2400, """
        全面掌握高中必修词汇和短语，系统整理教材重点词组和语法点。配合课本做基础题和高考基础题，培养良好做题习惯。
        重点关注长难句分析和翻译技巧，强化语法基础，每周做主题写作训练，为后期高考备考打好基础。""");

        // 初中阶段 - 以中考为导向
        learningBuckets.put(1500, """
        系统背诵中考大纲词汇和重点短语，重点突破易错词组。每天坚持做中考真题，特别是完形填空和阅读理解部分。
        总结选项陷阱和答题技巧，培养做题速度，定期进行听说训练。规范书面表达，为中考冲刺做准备。""");

        learningBuckets.put(1200, """
        重点攻克初中教材词汇和常用短语，注意区分近义词和易混词。系统做课内题和中考真题，重视语法填空和短文改错。
        坚持听力训练，培养英语思维，规范完成书面作业，为冲刺中考打好基础。""");

        learningBuckets.put(1000, """
        全面掌握初中基础词汇，重点记忆教材中的重点词组和短语。认真完成课内练习，掌握基本语法规则和句型变化。
        开始接触简单的中考题型，培养良好的英语学习习惯，为进入初三复习打好基础。""");

        // 小学高年级 - 以升学考试为导向
        learningBuckets.put(800, """
        重点掌握小升初常考词汇和核心短语，通过朗读课文来巩固发音和语感。做好课内题和历年小升初真题，注重基础知识积累。
        强化单项选择和完形填空训练，培养简单的听说读写能力，为小升初考试做好充分准备。""");

        learningBuckets.put(650, """
        系统学习教材词汇和常用短语，重点关注拼写规则和发音技巧。认真完成课内测试题，注意总结语法要点。
        通过听读训练提升语感，规范书写，为升学考试奠定良好基础。""");

        // 小学中低年级 - 以期末考试为导向
        learningBuckets.put(450, """
        认真学习教材单词和基本句型，重点关注拼读规则。完成课内练习和单元测试，培养课堂听说能力。
        勤于朗读，规范书写，重视课堂参与，为期末考试做好准备。""");

        learningBuckets.put(350, """
        掌握课本基础词汇，重点练习拼写规则。认真完成课后作业，积极参与课堂活动。
        坚持每日朗读，培养英语兴趣，为年级升学打好基础。""");

        learningBuckets.put(150, """
        认真记忆课本单词，注意基本拼写规则。按时完成课后练习，积极参与课堂互动。
        培养良好的学习习惯，为期末考试做好准备。""");

        learningBuckets.put(50, """
        巩固字母认读，学习简单单词拼写。认真完成课后作业，积极参与课堂活动。
        培养英语学习兴趣，为后续学习打好基础。""");

        learningBuckets.put(0, """
        从字母和基本单词开始，通过趣味活动培养学习兴趣。完成基础练习，参与课堂游戏。
        养成良好的听读习惯，为今后英语学习打好基础。""");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(3500, "高三水平");
        gradeLevels.put(2800, "高二水平");
        gradeLevels.put(2400, "高一水平");
        gradeLevels.put(1500, "初三水平");
        gradeLevels.put(1200, "初二水平");
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