# VBTest - 词汇量测试应用

VBTest 是一个稳健的、基于 Java 的自适应词汇测试应用，专为评估英语熟练程度而设计。它利用项目反应理论 (IRT) 来动态估算用户的词汇量和能力水平。

## 🛠 技术栈

*   **语言**: Java 17+
*   **构建工具**: Gradle
*   **架构**: 领域驱动设计 (DDD)
*   **核心算法**: IRT (项目反应理论) ，包含自定义偏差修正
*   **UI/报告**: HTML5 + Tailwind CSS (通过 CDN 引入)

## 🏗 项目架构

本项目遵循整洁架构原则，将领域逻辑与应用及基础设施关注点分离：

*   **`domain` (领域层)**: 包含核心业务逻辑和模型。
    *   `model`: `User` (用户), `Word` (单词), `Question` (问题), `TestResult` (测试结果)。
    *   `algorithm`: `IRTAlgorithm` 实现自适应测试逻辑。
*   **`application` (应用层)**: 编排测试流程。
    *   `TestService`, `TestManager`: 管理测试会话状态。
    *   `ReportGenerator`: 生成详细的 HTML 报告。
*   **`Main.java`**: 程序入口点，提供命令行界面 (CLI) 和编程接口 API。

## 🚀 快速开始

### 前置条件
*   已安装 JDK 17 或更高版本。
*   终端/命令提示符。

### 构建项目
使用内置的 Gradle wrapper 构建应用：

```bash
./gradlew build
```

## 📖 使用指南

VBTest 既可以作为交互式命令行工具使用，也可以通过其 Java API 集成到其他工作流中。

### 1. 交互式 CLI 模式

直接运行应用程序，在终端中启动测试会话：

```bash
java -cp build/classes/java/main Main
```

按照屏幕提示操作：
1.  输入您的姓名。
2.  (可选) 设置初始能力预估值（例如：3.0 对应初中水平）。
3.  回答自适应问题。

### 2. 编程集成 (API)

您可以编程调用测试逻辑并获取生成报告的路径。这对于将 VBTest 集成到自动化脚本或更大的系统中非常有用。

**方法签名:**
```java
public static String runTest(TestService testService, UserModel user)
```

**实现示例:**

```java
import application.TestManager;
import application.TestService;
import domain.WordBank;
import domain.algorithm.IRTAlgorithm;
import domain.model.UserModel;

public class MyIntegration {
    public static void main(String[] args) {
        // 1. 初始化核心组件
        WordBank wordBank = new WordBank("test_v9.6.csv");
        IRTAlgorithm algorithm = new IRTAlgorithm(wordBank);
        IRTAlgorithm.enableNoCorrectAnswerFeature(); // 可选配置
        
        TestManager testManager = new TestManager(algorithm, wordBank);
        TestService testService = new TestService(testManager);

        // 2. 设置用户
        UserModel user = new UserModel("user-id-123", "Alice");
        
        // 可选: 设置初始能力值 (0.0 - 7.5)
        // user.setInitialAbility(3.0); 
        // user.setCustomInitialAbility(true);

        // 3. 运行测试并获取报告路径
        // 注意: 这仍然会运行交互式 CLI 来回答问题
        String reportFile = Main.runTest(testService, user);
        
        System.out.println("测试完成。报告已生成于: " + reportFile);
    }
}
```

## 📊 报告生成

测试完成后，应用程序会生成一份综合的 HTML 报告。

*   **位置**: `report/` 目录。
*   **命名规范**: `vocabulary_report_{用户名}_{时间戳}.html`
*   **特性**:
    *   **Orchestrator 风格设计**: 翡翠绿 (Emerald Green) & 橙色 (Orange) 主题。
    *   **可视化**: 进度条显示能力水平（从小学到大学阶段）。
    *   **详细分析**:
        *   总题数 / 错题数 / 正确数统计。
        *   正确率百分比。
        *   初始能力预估值（如果已自定义）。
        *   逐题解析列表，包含正确性指示。

### 配置
您可以通过 `IRTAlgorithm` 中的静态方法自定义算法行为：
*   `IRTAlgorithm.enableNoCorrectAnswerFeature()`: 切换“以上都不对”选项的功能。
*   `IRTAlgorithm.setCustomInitialAbility(double val)`: 设置起始难度值。

## 📁 关键文件

*   `test_v9.6.csv`: 词汇数据库文件。
*   `src/main/java/Main.java`: 逻辑入口点。
*   `report/`: HTML 报告及相关产物存储目录。
