package domain.model;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ResultMean {

    int version = 0;


    public ResultMean(int version) {
        this.version = version;
    }


//    返回海报中的成绩解读
    public String getResultMean(int estimatedVocab){

        return getResultandVersion0(estimatedVocab);
    }

//    返回海报中的词汇量水平
    public String getGradeLevel(int estimatedVocab){
        return getGradeLevelVersion0(estimatedVocab);
    }

    private String getResultandVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> vocabLevels = new TreeMap<>();

        vocabLevels.put(6500, "达到了大学英语四级水平。优异水平！这是一个值得赞赏的成就。您现在应该能够较为流畅地阅读英语原版书籍，并能在大多数日常和学术场景中自如交流。建议您开始尝试更具挑战性的英语材料，如原版小说或学术论文，以进一步扩大词汇量。您的进步令人印象深刻，继续保持这样的学习热情！");
        vocabLevels.put(6000, "达到或超过了高中毕业水平。出色水平！这为高等教育阶段的英语学习打下了坚实基础。这意味着您可以理解大部分日常和学术英语材料，并能进行较为复杂的交流。建议您开始准备更高级的英语考试，如大学英语四级。您的努力正在开花结果，请继续保持这种学习动力！");
        vocabLevels.put(5500, "达到了高三水平。很好！您已经接近高中毕业的要求。这表明您具备了较强的英语基础，能够应对大多数高中阶段的英语学习任务。建议您着重关注词汇的实际应用，多进行阅读和写作练习。您已经走在了成功的道路上，再接再厉，相信您一定能实现更大的突破！");
        vocabLevels.put(4500, "达到了高二水平。不错！您正处于快速提升阶段。您现在应该能够理解较为复杂的文章，并能就熟悉的话题进行交流。建议您开始尝试英语原版材料，如简易版名著或英文报刊。您的进步值得肯定，继续努力，您将会在英语学习中取得更大的成就！");
        vocabLevels.put(4000, "达到了高一水平。加油！您正在为更高阶段的学习打好基础。这个阶段的词汇量允许您进行基本的日常交流，并能理解一般难度的文章。建议您开始有计划地背诵单词，并结合语境学习。您正在稳步前进，保持这种学习热情，您的英语能力一定会得到显著提升！");
        vocabLevels.put(3300, "达到了初中毕业水平。表现不错！您为进入高中阶段的英语学习做好了准备。您现在应该能够进行简单的日常对话，并理解基本的英语文章。建议您开始关注词组和常用表达，以提高语言的地道性。您的努力正在带来回报，继续坚持，更大的进步在等着您！");
        vocabLevels.put(3000, "达到了初三水平。继续努力！您正在为升入高中做最后冲刺。这个词汇量基础允许您理解大部分初中课本内容，并能就日常话题进行简单交流。建议您开始尝试用英语思考，增加英语的输出练习。您已经取得了很大的进步，相信通过持续努力，您一定能顺利过渡到高中英语学习！");
        vocabLevels.put(2500, "达到了初二水平。做得不错！您正在稳步提升您的英语能力。您现在应该能够理解简单的英语文章，并能进行基本的日常对话。建议您开始尝试简单的英语听力材料，提高听说能力。您的进步是显而易见的，继续保持这种学习劲头，您会发现英语越来越有趣！");
        vocabLevels.put(1500, "达到了初一水平。继续加油！您为进入初中英语学习打下了基础。这个阶段的词汇量使您能够理解基础英语句子，并能进行简单的问候和介绍。建议您开始注意单词的发音和拼写规则。您正在英语学习的道路上稳步前进，保持热情和耐心，您会取得更大的进步！");
        vocabLevels.put(850, "达到了小学毕业水平。表现不错！您已经掌握了基本的英语交流能力。您现在应该能够理解简单的英语指令，并能使用基本词汇进行简单对话。建议您开始尝试简单的英语阅读材料，如图画书或简单故事。您的努力正在开花结果，继续保持这种学习热情，英语世界的大门正在为您打开！");
        vocabLevels.put(600, "达到了小学五年级水平。做得好！您正在为义务教育阶段的英语学习做准备。您已经掌握了一些常用词汇，能够理解简单的英语句子。建议您开始尝试用这些词造句，增加语言的实际运用。您正在打造坚实的英语基础，继续努力，您将会在未来的学习中受益匪浅！");
        vocabLevels.put(300, "达到了小学四年级水平。继续努力！您正在积累基本的英语词汇。这个阶段您应该能认识一些日常用品和动作的英语表达。建议您尝试用图片或实物辅助记忆单词，使学习更加生动有趣。您正在英语学习的起跑线上稳步前进，保持这份热情，英语会成为您強大的工具！");
        vocabLevels.put(120, "达到了小学三年级水平。好的开始！您正在开始您的英语学习之旅。您已经认识了一些基本的英语单词，如数字、颜色等。建议您通过游戏和歌曲来学习更多单词，让英语学习变得有趣。记住，每个人都是从这里开始的，您已经迈出了重要的第一步，继续加油！");

        return vocabLevels.floorEntry(estimatedVocab).getValue();
    }

    private String getGradeLevelVersion0(int estimatedVocab) {
        NavigableMap<Integer, String> gradeLevels = new TreeMap<>();

        gradeLevels.put(6500, "达到了大学英语四级水平");
        gradeLevels.put(6000, "达到或超过了高中毕业水平");
        gradeLevels.put(5500, "达到了高三水平");
        gradeLevels.put(4500, "达到了高二水平");
        gradeLevels.put(4000, "达到了高一水平");
        gradeLevels.put(3300, "达到了初中毕业水平");
        gradeLevels.put(3000, "达到了初三水平");
        gradeLevels.put(2500, "达到了初二水平");
        gradeLevels.put(1500, "达到了初一水平");
        gradeLevels.put(850, "达到了小学毕业水平");
        gradeLevels.put(600, "达到了小学五年级水平");
        gradeLevels.put(300, "达到了小学四年级水平");
        gradeLevels.put(120, "达到了小学三年级水平");

        return gradeLevels.floorEntry(estimatedVocab).getValue();
    }

}
