package application;

import domain.model.Question;
import domain.model.TestResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ReportGenerator 类负责生成测试结果的 HTML 报告。
 */
public class ReportGenerator {

        public static void generateReport(TestResult result, String filePath) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write(buildHtmlContent(result));
                        System.out.println("报告已生成: " + new File(filePath).getAbsolutePath());
                } catch (IOException e) {
                        System.err.println("生成报告时出错: " + e.getMessage());
                }
        }

        private static String buildHtmlContent(TestResult result) {
                StringBuilder sb = new StringBuilder();
                sb.append("<!DOCTYPE html>\n");
                sb.append("<html lang=\"zh-CN\">\n");
                sb.append("<head>\n");
                sb.append("    <meta charset=\"UTF-8\">\n");
                sb.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
                sb.append("    <title>家庭CEO 伴学 · 词汇量测试诊断报告</title>\n");
                sb.append("    <script src=\"https://cdn.tailwindcss.com\"></script>\n");
                sb.append("    <style>\n");
                sb.append("        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap');\n");
                sb.append("\n");
                sb.append("        body {\n");
                sb.append("            font-family: 'Noto Sans SC', sans-serif;\n");
                sb.append("            background-color: #f8fafc;\n"); // slate-50
                sb.append("        }\n");
                sb.append("\n");
                sb.append("        .report-container {\n");
                sb.append("            width: 21cm;\n");
                sb.append("            min-height: 29.7cm;\n");
                sb.append("            margin: 2rem auto;\n");
                sb.append("            padding: 2cm 1.8cm;\n");
                sb.append("            background-color: white;\n");
                sb.append("            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);\n");
                sb.append("            border-radius: 2px;\n");
                sb.append("            /* Removed top border as requested */\n");
                sb.append("            display: flex;\n");
                sb.append("            flex-direction: column;\n");
                sb.append("        }\n");
                sb.append("\n");
                sb.append("        .status-badge {\n");
                sb.append("            padding: 0.2rem 0.6rem;\n");
                sb.append("            border-radius: 9999px;\n");
                sb.append("            font-size: 0.8rem;\n");
                sb.append("            font-weight: 500;\n");
                sb.append("            display: inline-flex;\n");
                sb.append("            align-items: center;\n");
                sb.append("            gap: 0.25rem;\n");
                sb.append("        }\n");
                sb.append("\n");
                sb.append("        .status-badge-correct {\n");
                sb.append("            background-color: #ecfdf5;\n"); // emerald-50
                sb.append("            color: #059669;\n"); // emerald-600
                sb.append("        }\n");
                sb.append("\n");
                sb.append("        .status-badge-incorrect {\n");
                sb.append("            background-color: #fef2f2;\n");
                sb.append("            color: #b91c1c;\n");
                sb.append("        }\n");
                sb.append("\n");
                sb.append("        .appendix-section {\n");
                sb.append("            page-break-before: always;\n");
                sb.append("        }\n");
                sb.append("    </style>\n");
                sb.append("</head>\n");
                sb.append("<body>\n");
                sb.append("    <div class=\"report-container\">\n");
                sb.append("        <!-- Page Header -->\n");
                sb.append("        <div class=\"flex justify-between items-center mb-10 pb-4 border-b border-slate-100\">\n");
                sb.append("            <img src=\"sample/logo.png\" alt=\"家庭CEO 伴学 Logo\" class=\"h-auto w-24\">\n");
                sb.append("            <div class=\"text-right\">\n");
                sb.append("                <a href=\"https://familyceo.cn\" target=\"_blank\"\n");
                sb.append("                    class=\"text-sm font-medium text-slate-500 hover:text-emerald-600 hover:underline block\">familyceo.cn</a>\n");
                sb.append("            </div>\n");
                sb.append("        </div>\n");
                sb.append("\n");
                sb.append("        <!-- Main Title -->\n");
                sb.append("        <header class=\"text-center mb-12\">\n");
                sb.append("            <h1 class=\"text-4xl font-bold text-slate-800 tracking-tight\">词汇量测试诊断报告</h1>\n");
                sb.append("            <p class=\"text-slate-500 mt-2 text-lg\">Vocabulary Assessment Report</p>\n");
                sb.append("        </header>\n");
                sb.append("\n");
                sb.append("        <!-- 学生信息 (Green Theme) -->\n");
                sb.append("        <section class=\"mb-8 p-6 bg-white rounded-xl shadow-sm border-t-4 border-emerald-500 border-x border-b border-slate-100\">\n");
                sb.append("            <div class=\"flex justify-between items-center mb-6\">\n");
                sb.append("                <h2 class=\"text-xl font-bold text-slate-800 flex items-center gap-2\">\n");
                sb.append("                    <span class=\"w-2 h-6 bg-emerald-500 rounded-full\"></span>\n");
                sb.append("                    基本信息\n");
                sb.append("                </h2>\n");
                sb.append("            </div>\n");
                sb.append("            <div class=\"grid grid-cols-1 md:grid-cols-2 gap-8\">\n");
                sb.append("                <div class=\"flex items-center gap-4\">\n");
                sb.append("                    <div class=\"bg-emerald-50 p-4 rounded-full\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\"\n");
                sb.append("                            height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"\n");
                sb.append("                            stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"h-6 w-6 text-emerald-600\">\n");
                sb.append("                            <path d=\"M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2\" />\n");
                sb.append("                            <circle cx=\"12\" cy=\"7\" r=\"4\" />\n");
                sb.append("                        </svg></div>\n");
                sb.append("                    <div>\n");
                sb.append("                        <p class=\"text-sm text-slate-500 mb-1\">学生姓名</p>\n");
                sb.append("                        <p class=\"font-bold text-slate-800 text-xl\">")
                                .append(result.getUser().getName()).append("</p>\n");
                sb.append("                    </div>\n");
                sb.append("                </div>\n");
                sb.append("                <div class=\"flex items-center gap-4\">\n");
                sb.append("                    <div class=\"bg-emerald-50 p-4 rounded-full\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\"\n");
                sb.append("                            height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"\n");
                sb.append("                            stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"h-6 w-6 text-emerald-600\">\n");
                sb.append("                            <rect width=\"18\" height=\"18\" x=\"3\" y=\"4\" rx=\"2\" ry=\"2\" />\n");
                sb.append("                            <line x1=\"16\" x1=\"16\" y1=\"2\" y2=\"6\" />\n");
                sb.append("                            <line x1=\"8\" x1=\"8\" y1=\"2\" y2=\"6\" />\n");
                sb.append("                            <line x1=\"3\" x1=\"21\" y1=\"10\" y2=\"10\" />\n");
                sb.append("                        </svg></div>\n");
                sb.append("                    <div>\n");
                sb.append("                        <p class=\"text-sm text-slate-500 mb-1\">测试日期</p>\n");
                sb.append("                        <p class=\"font-bold text-slate-800 text-xl\">")
                                .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")))
                                .append("</p>\n");
                sb.append("                    </div>\n");
                sb.append("                </div>\n");
                sb.append("            </div>\n");
                sb.append("            <div class=\"mt-8 pt-6 border-t border-slate-100 flex items-center justify-between\">\n");
                sb.append("                 <p class=\"text-slate-600 font-medium\">当前预估词汇量</p>\n");
                sb.append("                 <!-- Orange Highlight for Result -->\n");
                sb.append("                 <div class=\"bg-orange-500 text-white px-6 py-2 rounded-lg shadow-md\">\n");
                sb.append("                     <span class=\"text-2xl font-bold\">")
                                .append(result.getEstimatedVocabularySize()).append("</span>\n");
                sb.append("                     <span class=\"text-orange-50 text-sm ml-1\">words</span>\n");
                sb.append("                 </div>\n");
                sb.append("            </div>\n");
                sb.append("        </section>\n");
                sb.append("\n");
                sb.append("        <!-- 测试分析与建议 (Green Theme consistent with \"Especially Green\") -->\n");
                sb.append("        <section class=\"mb-8 p-6 bg-white rounded-xl shadow-sm border-t-4 border-emerald-500 border-x border-b border-slate-100\">\n");
                sb.append("            <h2 class=\"text-xl font-bold text-slate-800 mb-6 flex items-center gap-2\">\n");
                sb.append("                <span class=\"w-2 h-6 bg-emerald-500 rounded-full\"></span>\n");
                sb.append("                测试分析与建议\n");
                sb.append("            </h2>\n");
                sb.append("\n");
                sb.append("            <div class=\"mb-8\">\n");
                sb.append("                <h3 class=\"text-lg font-semibold text-slate-800 mb-4\">能力评估</h3>\n");

                // Add Progress Bar
                sb.append(buildProgressBar(result.getEstimatedVocabularySize()));

                sb.append("                <div class=\"bg-slate-50 p-4 rounded-lg border border-slate-200 mt-4\">\n");
                sb.append("                    <p class=\"text-slate-700 leading-relaxed text-justify\">");
                sb.append(result.getResultMean().getResultMean(result.getEstimatedVocabularySize()));
                sb.append("</p>\n");
                sb.append("                </div>\n");
                sb.append("            </div>\n");
                sb.append("\n");
                sb.append("            <div>\n");
                sb.append("                <h3 class=\"text-lg font-semibold text-slate-800 mb-3\">学习建议</h3>\n");
                sb.append("                <ul class=\"list-disc list-outside ml-5 text-slate-700 space-y-2 leading-relaxed\">\n");
                sb.append("                    <li class=\"pl-2 text-justify\">");
                sb.append(result.getResultMean().getResultBucket(result.getEstimatedVocabularySize()));
                sb.append("</li>\n");
                sb.append("                </ul>\n");
                sb.append("            </div>\n");
                sb.append("        </section>\n");
                sb.append("\n");
                // 错题重点展示
                sb.append("        <section class=\"mb-8\">\n");
                sb.append("             <h2 class=\"text-2xl font-semibold text-slate-700 mb-4\">错题解析</h2>\n");
                sb.append("             <div class=\"space-y-4\">\n");

                List<Question> questions = result.getAnsweredQuestions();
                boolean hasWrong = false;

                for (int i = 0; i < questions.size(); i++) {
                        Question q = questions.get(i);
                        if (!q.isAnsweredCorrectly()) {
                                hasWrong = true;
                                sb.append("                <div class=\"p-5 border border-slate-200 rounded-lg bg-white shadow-sm\">\n");
                                sb.append("                    <p class=\"font-semibold text-slate-800 text-lg flex items-center gap-2\">\n");
                                sb.append("                        <span class=\"text-rose-500\">Question ")
                                                .append(i + 1).append("</span>\n");
                                sb.append("                        <span class=\"text-slate-300\">|</span>\n");
                                sb.append("                        <span>").append(q.getWord().getWord())
                                                .append("</span>\n");
                                sb.append("                    </p>\n");
                                sb.append("                    <div class=\"mt-3 text-sm space-y-2\">\n");

                                int userIndex = q.getUserAnswerIndex();
                                int correctIndex = q.getCorrectOptionIndex();
                                List<String> options = q.getOptions();

                                for (int j = 0; j < options.size(); j++) {
                                        String optionText = options.get(j);
                                        String itemClass = "p-3 rounded-md transition-colors";
                                        String prefix = (j + 1) + ". ";

                                        if (j == correctIndex) {
                                                itemClass += " bg-emerald-50 text-emerald-800 font-medium border border-emerald-200";
                                                prefix += " <span class=\"text-emerald-600 font-bold ml-1\">(Correct)</span> ";
                                        } else if (j == userIndex) {
                                                itemClass += " bg-rose-50 text-rose-800 font-medium border border-rose-200";
                                                prefix += " <span class=\"text-rose-600 font-bold ml-1\">(Your Choice)</span> ";
                                        } else {
                                                itemClass += " text-slate-600 bg-slate-50";
                                        }

                                        sb.append("                        <div class=\"").append(itemClass)
                                                        .append("\">");
                                        sb.append(prefix).append(optionText);
                                        sb.append("</div>\n");
                                }

                                sb.append("                    </div>\n");
                                sb.append("                </div>\n");
                        }
                }

                if (!hasWrong) {
                        sb.append("                <div class=\"p-8 text-center bg-emerald-50 rounded-xl border border-emerald-100\">\n");
                        sb.append("                    <p class=\"text-emerald-800 font-medium text-lg\">🎉 太棒了！本次测试没有错题。</p>\n");
                        sb.append("                    <p class=\"text-emerald-600 mt-1\">Excellent work!</p>\n");
                        sb.append("                </div>\n");
                }

                sb.append("             </div>\n");
                sb.append("        </section>\n");
                sb.append("\n");

                // 详细分析表格
                int totalQuestions = questions.size();
                int wrongAnswers = (int) questions.stream().filter(q -> !q.isAnsweredCorrectly()).count();
                int correctAnswers = totalQuestions - wrongAnswers;
                double accuracy = (double) correctAnswers / totalQuestions * 100;

                sb.append("        <section class=\"mb-8\">\n");
                sb.append("            <div class=\"flex items-baseline gap-4 mb-4\">\n");
                sb.append("                <h2 class=\"text-2xl font-semibold text-slate-700\">答题详细情况</h2>\n");
                sb.append("                <p class=\"text-sm text-slate-500\">\n");
                sb.append("                    共 <span class=\"font-medium\">").append(totalQuestions)
                                .append("</span> 题，\n");
                sb.append("                    错 <span class=\"font-medium text-rose-500\">").append(wrongAnswers)
                                .append("</span> 题，\n");
                sb.append("                    正确率 <span class=\"font-medium text-emerald-600\">")
                                .append(String.format("%.1f%%", accuracy)).append("</span>\n");

                if (result.getUser().isCustomInitialAbility()) {
                        sb.append("                    <span class=\"text-slate-300 mx-2\">|</span>\n");
                        sb.append("                    <span class=\"text-slate-400\">初始能力值: ")
                                        .append(String.format("%.1f", result.getUser().getInitialAbility()))
                                        .append("</span>\n");
                }

                sb.append("                </p>\n");
                sb.append("            </div>\n");
                sb.append("            <div class=\"overflow-x-auto border border-slate-200 rounded-lg shadow-sm\">\n");
                sb.append("                <table class=\"w-full text-sm\">\n");
                sb.append("                    <thead class=\"bg-slate-50\">\n");
                sb.append("                        <tr>\n");
                sb.append("                            <th class=\"p-3 font-semibold text-slate-600 border-b text-left w-12\">#</th>\n");
                sb.append("                            <th class=\"p-3 font-semibold text-slate-600 border-b text-left\">单词</th>\n");
                sb.append("                            <th class=\"p-3 font-semibold text-slate-600 border-b text-left\">含义</th>\n");
                sb.append("                            <th class=\"p-3 font-semibold text-slate-600 border-b text-left\">你的选择</th>\n");
                sb.append("                            <th class=\"p-3 font-semibold text-slate-600 border-b text-center\">状态</th>\n");
                sb.append("                        </tr>\n");
                sb.append("                    </thead>\n");
                sb.append("                    <tbody class=\"text-slate-700 bg-white\">\n");

                for (int i = 0; i < questions.size(); i++) {
                        Question q = questions.get(i);
                        boolean isCorrect = q.isAnsweredCorrectly();
                        String rowClass = isCorrect ? "hover:bg-slate-50 border-b border-slate-100"
                                        : "bg-rose-50 hover:bg-rose-100 border-b border-rose-100";

                        sb.append("                        <tr class=\"").append(rowClass).append("\">\n");
                        sb.append("                            <td class=\"p-3\">").append(i + 1).append("</td>\n");
                        sb.append("                            <td class=\"p-3 font-medium\">")
                                        .append(q.getWord().getWord()).append("</td>\n");
                        sb.append("                            <td class=\"p-3\">").append(q.getWord().getChinese())
                                        .append("</td>\n");

                        String userChoice = "";
                        int userIndex = q.getUserAnswerIndex();
                        if (userIndex >= 0 && userIndex < q.getOptions().size()) {
                                userChoice = q.getOptions().get(userIndex);
                        } else {
                                userChoice = "未选择";
                        }

                        String choiceClass = isCorrect ? "text-slate-700" : "text-rose-700 font-semibold";

                        sb.append("                            <td class=\"p-3 ").append(choiceClass).append("\">")
                                        .append(userChoice).append("</td>\n");
                        sb.append("                            <td class=\"p-3 text-center\">\n");
                        if (isCorrect) {
                                sb.append("                                <span class=\"status-badge status-badge-correct\">✓</span>\n");
                        } else {
                                sb.append("                                <span class=\"status-badge status-badge-incorrect\">✗</span>\n");
                        }
                        sb.append("                            </td>\n");
                        sb.append("                        </tr>\n");
                }

                sb.append("                    </tbody>\n");
                sb.append("                </table>\n");
                sb.append("            </div>\n");
                sb.append("        </section>\n");
                sb.append("\n");

                sb.append("    </div>\n");
                sb.append("</body>\n");
                sb.append("</html>");

                return sb.toString();
        }

        private static String buildProgressBar(int estimatedVocab) {
                // Scala: 0 - 7000
                int maxScale = 7000;
                double percentage = (double) estimatedVocab / maxScale * 100;
                if (percentage > 100)
                        percentage = 100;
                if (percentage < 0)
                        percentage = 0;

                StringBuilder sb = new StringBuilder();

                sb.append("<div class=\"w-full mt-4\">\n");

                // Labels (Top)
                sb.append("    <div class=\"flex mb-1 text-xs text-slate-500 font-medium relative h-4\">\n");
                sb.append("        <div class=\"absolute text-center\" style=\"left: 3.5%; transform: translateX(-50%);\">小学</div>\n");
                sb.append("        <div class=\"absolute text-center\" style=\"left: 17.8%; transform: translateX(-50%);\">初中</div>\n");
                sb.append("        <div class=\"absolute text-center\" style=\"left: 46.4%; transform: translateX(-50%);\">高中</div>\n");
                sb.append("        <div class=\"absolute text-center\" style=\"left: 82.1%; transform: translateX(-50%);\">大学+</div>\n");
                sb.append("    </div>\n");

                // Bar Container
                sb.append("    <div class=\"relative h-3 bg-slate-100 rounded-full overflow-hidden flex\">\n");

                // Segments - updated to Orange -> Green gradient feel
                // 500 / 7000 = 7.14% (Orange)
                sb.append("        <div class=\"h-full bg-orange-200\" style=\"width: 7.14%\"></div>\n");
                // (2000-500)/7000 = 21.43% (Amber)
                sb.append("        <div class=\"h-full bg-amber-200\" style=\"width: 21.43%\"></div>\n");
                // (4500-2000)/7000 = 35.71% (Lime)
                sb.append("        <div class=\"h-full bg-lime-200\" style=\"width: 35.71%\"></div>\n");
                // Remainder = 35.72% (Emerald)
                sb.append("        <div class=\"h-full bg-emerald-200\" style=\"flex: 1\"></div>\n");

                // Progress Marker
                sb.append("        <div class=\"absolute top-0 bottom-0 w-1 bg-slate-800 shadow-md transform -translate-x-1/2\" style=\"left: ")
                                .append(String.format("%.1f", percentage)).append("%;\"></div>\n");

                sb.append("    </div>\n");

                // Scale Labels (Bottom)
                sb.append("    <div class=\"flex mt-1 text-xs text-slate-400 relative h-4\">\n");
                sb.append("        <span class=\"absolute left-0\">0</span>\n");
                sb.append("        <span class=\"absolute\" style=\"left: 7.14%; transform: translateX(-50%);\">500</span>\n");
                sb.append("        <span class=\"absolute\" style=\"left: 28.57%; transform: translateX(-50%);\">2000</span>\n");
                sb.append("        <span class=\"absolute\" style=\"left: 64.28%; transform: translateX(-50%);\">4500</span>\n");
                sb.append("        <span class=\"absolute right-0\">7000+</span>\n");
                sb.append("    </div>\n");

                sb.append("</div>\n");

                return sb.toString();
        }
}
