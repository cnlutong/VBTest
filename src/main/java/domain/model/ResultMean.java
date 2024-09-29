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
        // 将6500映射到3500
        return Math.min(3500, (int) (estimatedVocab * (3500.0 / 6300.0)));
    }

    // 返回成绩解读
 private String getResultInterpretation(int estimatedVocab) {
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        vocabLevels.put(3500, "您的词汇量已达到高三水平，接近高考要求。这个水平的词汇量足以应对大多数高考英语题型，包括阅读理解和写作部分。建议您继续扩大词汇量，特别是高考大纲中的超纲词汇，并着重提高词汇的灵活运用能力。");

        vocabLevels.put(2800, "您的词汇量达到了高二水平。这个阶段的词汇量使您能够理解较复杂的英语文章，并能就大多数话题进行交流。建议您开始系统性地备战高考，重点关注高考常见题型和高频词汇的应用。");

        vocabLevels.put(2400, "您的词汇量达到了高一水平。这个词汇量水平允许您理解大部分高中英语教材内容，并能进行基本的日常交流。建议您开始有计划地扩大词汇量，为备战高考打好基础。");

        vocabLevels.put(1600, "您的词汇量达到了初三水平，接近中考要求。这个词汇量基础能够帮助您应对大多数中考英语题型。建议您继续巩固和扩大词汇量，重点关注中考大纲词汇和语法点。");

        vocabLevels.put(1400, "您的词汇量达到了初二水平。这个阶段的词汇量使您能够理解大部分初中英语教材内容，并能进行简单的日常对话。建议您开始为中考做准备，系统性地学习中考词汇表。");

        vocabLevels.put(1200, "您的词汇量达到了初一水平。这个词汇量水平为您的初中英语学习打下了良好的基础。您应该能够理解基础的英语句子和段落，并能进行简单的日常交流。");

        vocabLevels.put(900, "您的词汇量达到了小学毕业水平。这表明您已经掌握了英语的基本词汇，能够理解简单的英语指令和短文。这是一个很好的起点，为您进入初中阶段的英语学习做好了准备。");

        vocabLevels.put(600, "您的词汇量达到了小学高年级水平。在这个阶段，您应该能够认识许多日常用品、动作和简单形容词的英语表达。建议您继续积累词汇，为小学毕业考试做准备。");

        vocabLevels.put(300, "您的词汇量达到了小学中年级水平。这个阶段您已经学会了一些基本的英语单词，如数字、颜色、简单的动物和物品名称等。继续保持学习热情，词汇量会稳步提升。");

        vocabLevels.put(100, "您的词汇量处于小学低年级水平。这个阶段您可能已经学会了英语字母表，能够认识一些简单的单词如"cat"、"dog"或数字等。这是英语学习的重要起点。");

        vocabLevels.put(0, "您正处于英语学习的起步阶段。虽然词汇量还很有限，但这是每个人学习英语的必经阶段。通过日常学习和练习，您的词汇量会逐步提升。");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }



    // 返回学习建议
 
    private String getLearningBucket(int estimatedVocab) {
        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();

        learningBuckets.put(3500, "建议您重点关注高考英语大纲词汇，特别是超纲词汇和易混淆词组。每天背诵并造句，巩固词汇运用能力。同时，多做高考真题和模拟题，特别注重阅读理解和完形填空部分的练习。定期进行高考英语听力训练，提高对各种口音的适应能力。针对高考作文，可以背诵优秀范文，积累常用表达和句型。");

        learningBuckets.put(2800, "建议您系统复习高中英语教材，重点掌握教材中的重点词组和固定搭配。每天学习15-20个词组，注意在语境中理解和运用。针对性地训练高考英语的各个题型，如完形填空、阅读理解等。定期做英语听力练习，提高对不同话题和场景的理解能力。练习高考英语作文，背诵常用模板和素材。");

        learningBuckets.put(2400, "建议您重点复习高中英语必修课程中的词汇和语法点。每天背诵10-15个高频词汇及其用法，巩固重要语法知识。开始接触高考英语真题，熟悉题型和答题技巧。针对性地训练英语听力，特别是长对话和短文理解。定期练习英语短文写作，积累常用句型和表达方式。");

        learningBuckets.put(1600, "建议您系统复习初中英语教材，重点掌握中考大纲词汇。每天记忆并运用10-15个词汇，注意在实际语境中使用。针对性地练习中考英语各题型，尤其是完形填空和阅读理解。每周做2-3次中考英语听力训练，提高听力理解能力。定期练习中考英语书面表达，掌握常见话题的写作要点。");

        learningBuckets.put(1400, "建议您重点复习初中英语教材中的重点单元，特别注意课文中的常用表达和句型。每天背诵8-10个新词，并在造句中运用。开始接触中考英语真题，熟悉题型和答题技巧。定期进行英语听力训练，提高对日常对话的理解能力。尝试用所学词汇和句型写简单的段落。");

        learningBuckets.put(1200, "建议您全面复习小学英语教材，为初中英语学习打好基础。系统复习小学阶段的词汇，每天学习5-8个新词，注意拼写和发音。做一些针对性的小学毕业水平测试题，查漏补缺。开始尝试简单的英语听力训练，为初中英语学习做准备。练习用简单的英语句子描述日常生活。");

        learningBuckets.put(900, "建议您重点复习小学高年级英语教材，特别是五年级和六年级的重点内容。每天学习5-6个新词，并在简单的句子中运用。定期做一些基础的英语听力练习，提高音素辨识能力。尝试写简单的英语短文，描述自己的日常生活或兴趣爱好。");

        learningBuckets.put(600, "建议您系统学习小学中年级英语教材，重点掌握教材中的基础词汇和简单句型。每天学习4-5个新词，注意正确发音和拼写。做一些针对性的英语拼写和语音练习，提高基础能力。尝试朗读简单的英语短文，培养语感和流利度。");

        learningBuckets.put(300, "建议您重点学习小学低年级英语教材，掌握基本的字母拼读规则和简单词汇。每天练习字母发音和简单单词的拼读。通过看图说话等方式，学习描述日常生活的简单句子。尝试背诵一些简单的英语儿歌或对话，培养语感。");

        learningBuckets.put(100, "建议您从学习英语字母和数字开始，为今后的英语学习打好基础。每天认识1-2个字母，并学习与之相关的简单单词。通过英语儿歌和简单的互动游戏，增加对英语的兴趣和感知。尝试用英语说出一些身边常见物品的名称。");

        learningBuckets.put(0, "建议您通过图片和实物来认识一些最基本的英语单词，如常见的颜色、动物和水果等。使用字母卡片和拼图等工具，培养对英语的初步认知。每天听一些简单的英语儿歌，感受英语的韵律和节奏。通过模仿和重复，学习最基本的英语问候语。");

        return learningBuckets.floorEntry(estimatedVocab).getValue();
    }


    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(3500, "高三水平");
        gradeLevels.put(2800, "高二水平");
        gradeLevels.put(2400, "高一水平");
        gradeLevels.put(1600, "初三水平");
        gradeLevels.put(1500, "初二水平");
        gradeLevels.put(1400, "初一水平");
        gradeLevels.put(1200, "小学六年级水平");
        gradeLevels.put(900, "小学五年级水平");
        gradeLevels.put(700, "小学四年级水平");
        gradeLevels.put(400, "小学三年级水平");
        gradeLevels.put(150, "小学二年级水平");
        gradeLevels.put(50, "小学一年级水平");
        gradeLevels.put(0, "学龄前水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }
}
//package domain.model;
//
//import java.util.NavigableMap;
//import java.util.TreeMap;
//
//
////词汇量测试的海报需要的数据从这里得到
////为了适应市场宣传中统一口径，这里将对测试出的词汇量数值进行修正。
//
//public class ResultMean {
//
//    int version = 0;
//
//    public ResultMean(int version) {
//        this.version = version;
//    }
//
//    // 返回海报中的词汇量水平
//    public String getGradeLevel(int estimatedVocab) {
//        return getGradeLevelVersion0(estimatedVocab);
//    }
//
//    // 返回成绩中的成绩解读
//    public String getResultMean(int estimatedVocab) {
//        return getResultInterpretation(estimatedVocab);
//    }
//
//    // 返回成绩中的学习建议
//    public String getResultBucket(int estimatedVocab) {
//        return getLearningBucket(estimatedVocab);
//    }
//
//    // 返回成绩解读
//    private String getResultInterpretation(int estimatedVocab) {
//        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();
//
//        vocabLevels.put(6500, "达到了大学英语四级水平。优异水平！这是一个值得赞赏的成就。您现在应该能够较为流畅地阅读英语原版书籍，并能在大多数日常和学术场景中自如交流。");
//        vocabLevels.put(6000, "达到或超过了高中毕业水平。出色水平！这为高等教育阶段的英语学习打下了坚实基础。这意味着您可以理解大部分日常和学术英语材料，并能进行较为复杂的交流。");
//        vocabLevels.put(5500, "达到了高三水平。很好！您已经接近高中毕业的要求。这表明您具备了较强的英语基础，能够应对大多数高中阶段的英语学习任务。");
//        vocabLevels.put(4500, "达到了高二水平。不错！您正处于快速提升阶段。您现在应该能够理解较为复杂的文章，并能就熟悉的话题进行交流。");
//        vocabLevels.put(4000, "达到了高一水平。加油！您正在为更高阶段的学习打好基础。这个阶段的词汇量允许您进行基本的日常交流，并能理解一般难度的文章。");
//        vocabLevels.put(3300, "达到了初中毕业水平。表现不错！您为进入高中阶段的英语学习做好了准备。您现在应该能够进行简单的日常对话，并理解基本的英语文章。");
//        vocabLevels.put(3000, "达到了初三水平。继续努力！您正在为升入高中做最后冲刺。这个词汇量基础允许您理解大部分初中课本内容，并能就日常话题进行简单交流。");
//        vocabLevels.put(2500, "达到了初二水平。做得不错！您正在稳步提升您的英语能力。您现在应该能够理解简单的英语文章，并能进行基本的日常对话。");
//        vocabLevels.put(1500, "达到了初一水平。继续加油！您为进入初中英语学习打下了基础。这个阶段的词汇量使您能够理解基础英语句子，并能进行简单的问候和介绍。");
//        vocabLevels.put(850, "达到了小学毕业水平。表现不错！您已经掌握了基本的英语交流能力。您现在应该能够理解简单的英语指令，并能使用基本词汇进行简单对话。");
//        vocabLevels.put(600, "达到了小学五年级水平。做得好！您正在为义务教育阶段的英语学习做准备。您已经掌握了一些常用词汇，能够理解简单的英语句子。");
//        vocabLevels.put(300, "达到了小学四年级水平。继续努力！您正在积累基本的英语词汇。这个阶段您应该能认识一些日常用品和动作的英语表达。");
//        vocabLevels.put(120, "达到了小学三年级水平。好的开始！您正在开始您的英语学习之旅。您已经认识了一些基本的英语单词，如数字、颜色等。");
//        vocabLevels.put(0, "继续加油");
//
//        return vocabLevels.floorEntry(estimatedVocab).getValue();
//    }
//
//    // 返回学习建议
//    private String getLearningBucket(int estimatedVocab) {
//        NavigableMap<Integer, String> learningBuckets = new TreeMap<>();
//
//        learningBuckets.put(6500, "建议您开始尝试更具挑战性的英语材料，如原版小说或学术论文，以进一步扩大词汇量。您的进步令人印象深刻，继续保持这样的学习热情！");
//        learningBuckets.put(6000, "建议您开始准备更高级的英语考试，如大学英语四级。您的努力正在开花结果，请继续保持这种学习动力！");
//        learningBuckets.put(5500, "建议您着重关注词汇的实际应用，多进行阅读和写作练习。您已经走在了成功的道路上，再接再厉，相信您一定能实现更大的突破！");
//        learningBuckets.put(4500, "建议您开始尝试英语原版材料，如简易版名著或英文报刊。您的进步值得肯定，继续努力，您将会在英语学习中取得更大的成就！");
//        learningBuckets.put(4000, "建议您开始有计划地背诵单词，并结合语境学习。您正在稳步前进，保持这种学习热情，您的英语能力一定会得到显著提升！");
//        learningBuckets.put(3300, "建议您开始关注词组和常用表达，以提高语言的地道性。您的努力正在带来回报，继续坚持，更大的进步在等着您！");
//        learningBuckets.put(3000, "建议您开始尝试用英语思考，增加英语的输出练习。您已经取得了很大的进步，相信通过持续努力，您一定能顺利过渡到高中英语学习！");
//        learningBuckets.put(2500, "建议您开始尝试简单的英语听力材料，提高听说能力。您的进步是显而易见的，继续保持这种学习劲头，您会发现英语越来越有趣！");
//        learningBuckets.put(1500, "建议您开始注意单词的发音和拼写规则。您正在英语学习的道路上稳步前进，保持热情和耐心，您会取得更大的进步！");
//        learningBuckets.put(850, "建议您开始尝试简单的英语阅读材料，如图画书或简单故事。您的努力正在开花结果，继续保持这种学习热情，英语世界的大门正在为您打开！");
//        learningBuckets.put(600, "建议您开始尝试用这些词造句，增加语言的实际运用。您正在打造坚实的英语基础，继续努力，您将会在未来的学习中受益匪浅！");
//        learningBuckets.put(300, "建议您尝试用图片或实物辅助记忆单词，使学习更加生动有趣。您正在英语学习的起跑线上稳步前进，保持这份热情，英语会成为您強大的工具！");
//        learningBuckets.put(120, "建议您通过游戏和歌曲来学习更多单词，让英语学习变得有趣。记住，每个人都是从这里开始的，您已经迈出了重要的第一步，继续加油！");
//        learningBuckets.put(0, "继续加油吧！");
//
//        return learningBuckets.floorEntry(estimatedVocab).getValue();
//    }
//
//    private String getGradeLevelVersion0(int estimatedVocab) {
//        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();
//
//        gradeLevels.put(6500, "大学英语四级水平");
//        gradeLevels.put(6000, "高中毕业水平");
//        gradeLevels.put(5500, "高三水平");
//        gradeLevels.put(4500, "高二水平");
//        gradeLevels.put(4000, "高一水平");
//        gradeLevels.put(3300, "初中毕业水平");
//        gradeLevels.put(3000, "初三水平");
//        gradeLevels.put(2500, "初二水平");
//        gradeLevels.put(1500, "初一水平");
//        gradeLevels.put(850, "小学毕业水平");
//        gradeLevels.put(600, "小学五年级水平");
//        gradeLevels.put(300, "小学四年级水平");
//        gradeLevels.put(120, "小学三年级水平");
//        gradeLevels.put(0, "学龄前水平");
//
//        return gradeLevels.floorEntry(estimatedVocab).getValue();
//    }
//}