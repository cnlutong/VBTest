package domain;

import domain.model.ResultMean;


// 中文标题
// 用于海报中展示信息
// 需要在创建时给出
// 学生的测试结果（词汇量数目）
// 姓名
// 学校名
// 年纪
// 词库单词总数

//请参阅 标注版.png

public class Certificate {
    private final String certificateName;
    private final int estimatedVocab;
    private final ResultMean resultMean;
    private final String userName;
    private final String userSchoolName;
    private final String userClass;
    private final int totalWordSize;

    public Certificate(String certificateName, int estimatedVocab, String userName, String userSchoolName, String userClass, int totalWordSize) {
        this.certificateName = certificateName;
        this.estimatedVocab = estimatedVocab;
        this.userName = userName;
        this.userSchoolName = userSchoolName;
        this.userClass = userClass;
        this.totalWordSize = totalWordSize;
        this.resultMean = new ResultMean(0);
    }



    // 返回海报中的词汇量水平，对应信息6
    public String getGradeLevel() {
        return resultMean.getGradeLevel(estimatedVocab);
    }

    // 返回成绩中的成绩解读，对应信息8
    public String getResultMean() {
        return resultMean.getResultMean(estimatedVocab);
    }

    // 返回成绩中的学习建议，对应信息9
    public String getResultBucket() {
        return resultMean.getResultBucket(estimatedVocab);
    }

    // 获取测试报告中文标题，对应信息1
    public String getCertificateName() {
        return certificateName;
    }

    // 获取海报中展示的词库信息，对应信息2
    public String getInfo() {
        return "中小学课表单词，共" + this.totalWordSize + "词";
    }

    // 获取海报中展示的学生名，对应信息3
    public String getUserName() {
        return userName;
    }

    // 获取海报中展示的学生学校，对应信息4
    public String getUserSchoolName() {
        return userSchoolName;
    }

    // 获取海报中展示的学生年纪，对应信息5
    public String getUserClass() {
        return userClass;
    }

}
