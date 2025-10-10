# 英语词汇量检测系统

基于IRT（项目反应理论）算法的自适应英语词汇量测试系统，通过智能出题评估用户的词汇水平并生成个性化学习建议。

## 🚀 核心特性

- **自适应测试算法**：基于IRT理论，根据答题情况动态调整题目难度
- **智能评估系统**：准确评估词汇量水平（学龄前至大学六级）
- **个性化报告**：生成详细的测试报告和学习建议
- **证书生成**：支持生成包含用户信息的测试证书
- **多版本词库**：支持不同难度等级的词库文件

## 🛠 技术栈

- **语言**：Java 17+
- **构建工具**：Gradle
- **测试框架**：JUnit 5
- **算法**：IRT（项目反应理论）

## 📁 项目结构

```
src/main/java/
├── Main.java                    # 程序入口
├── application/                 # 应用层
│   ├── TestManager.java        # 测试管理器
│   └── TestService.java        # 测试服务
├── domain/                      # 领域层
│   ├── Certificate.java        # 证书信息类
│   ├── WordBank.java          # 词库管理
│   ├── algorithm/              # 算法实现
│   │   ├── IRTAlgorithm.java  # IRT算法核心
│   │   └── TestAlgorithm.java # 算法接口
│   └── model/                  # 领域模型
│       ├── Question.java      # 题目模型
│       ├── ResultMean.java    # 结果解读
│       ├── TestResult.java    # 测试结果
│       ├── UserModel.java     # 用户模型
│       └── Word.java          # 单词模型
```

## 🎯 快速开始

### 1. 环境要求
- Java 17 或更高版本
- Gradle 7.0+

## 🔧 二次开发指南

### 核心类说明

#### 1. IRTAlgorithm（IRT算法核心）
- **位置**：`domain.algorithm.IRTAlgorithm`
- **功能**：实现自适应测试算法，动态调整题目难度
- **关键参数**：
  - `MIN_QUESTIONS = 10`：最少题目数
  - `MAX_QUESTIONS = 100`：最多题目数
  - `INITIAL_ABILITY = 1.0`：初始能力值

#### 2. Certificate（证书生成）
- **位置**：`domain.Certificate`
- **功能**：封装测试结果信息，用于生成证书
- **包含信息**：用户名、学校、年级、词汇量、等级评定等

#### 3. WordBank（词库管理）
- **位置**：`domain.WordBank`
- **功能**：加载和管理词库，支持按难度筛选单词
- **支持格式**：CSV文件（id,word,chinese,difficulty）

#### 4. ResultMean（结果解读）
- **位置**：`domain.model.ResultMean`
- **功能**：根据词汇量提供等级评定和学习建议
- **等级范围**：学龄前 → 小学 → 初中 → 高中 → 大学四级/六级

### 扩展点

#### 1. 添加新的测试算法
实现 `TestAlgorithm` 接口：
```java
public class CustomAlgorithm implements TestAlgorithm {
    // 实现自定义算法逻辑
}
```

#### 2. 自定义词库格式
继承 `WordBank` 类或修改 CSV 解析逻辑

#### 3. 扩展结果评估
修改 `ResultMean` 类中的评级标准和建议内容

#### 4. 添加新的题目类型
扩展 `Question` 类，支持更多题型（如听力、语法等）

### 配置说明

#### 算法参数调整
在 `IRTAlgorithm` 类中可调整：
- 测试题目数量范围
- 难度调整策略
- 能力值估算参数

#### 词库切换
在 `Main.java` 中修改词库文件路径：
```java
WordBank wordBank = initializeWordBank("your_wordbank.csv");
```
