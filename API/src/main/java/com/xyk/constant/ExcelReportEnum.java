package com.xyk.constant;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 徐亚奎
 * @date 2021-10-08 14:38
 * @Description
 */
@AllArgsConstructor
@ToString
public enum ExcelReportEnum {

    /**
     * [1-10]为 文件类型    [11-100]为 报表id
     * */
    /**
     * 两种文件类型
     * */
    /**
     * .xlsx文件类型
     * */
    XLSX(1001,".xlsx文件类型",".xlsx",".xlsx"),
    /**
     * .xls文件类型
     * */
    XLS(1002,".xls文件类型",".xls",".xlsx"),


    /**
     * 导出[考试成绩概况]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    ExamScoreOverView(11,"examScoreOverView","考试成绩概况",XLSX.fileType),
    /**
     * 导出[学科成绩分析]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    SubjectScoreAnalysisReport(12,"subjectScoreAnalysisReport","班级学科成绩分析",XLSX.fileType),
    /**
     * 导出[学业等级分布]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    AcademicLevelDistribution(13,"academicLevelDistribution","学业等级分析",XLSX.fileType),
    /**
     * 导出[成绩分数段对比]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    ScoreSegmentComparison(14,"scoreSegmentComparison","成绩分段对比",XLSX.fileType),
    /**
     * 导出[优秀生学困生]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    ExcellentAndStudyHardStudent(15,"excellentAndStudyHardStudent","优秀生学困生",XLSX.fileType),
    /**
     * 导出[题目分析]报表 2.0.1过渡版本 试卷报告详情-试卷分析-单科
     * */
    QuestionAnalysis(16,"questionAnalysis","题目分析",XLSX.fileType),
    /**
     * 导出[作答详情]报表 2.0.1过渡版本 试卷报告详情-总分-表格
     * */
    AnswerDetails(17,"answerDetails","作答详情",XLSX.fileType),
    /**
     * 导出[成绩单]报表 2.0.1过渡版本 考试报告-成绩单-单科/总科
     * */
    Transcript(18,"transcript","成绩单",XLSX.fileType),

    ;

    private final Integer id;
    private final String exportReporte;
    private final String value;
    private final String fileType;

    public static ExcelReportEnum findById(Integer id) {
        for (ExcelReportEnum e : ExcelReportEnum.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 获取报表的id和name的集合 [11-100)
     * */
    public static List<Map<String, Object>> getReportIdAndNameList() {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for(ExcelReportEnum anEnum : ExcelReportEnum.values()){
            Integer id = anEnum.getId();
            if (id>=10 && id<100){
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("reportName", anEnum.getValue());
                result.add(map);
            }
        }
        return result;
    }
    /**
     * 获取报表的id的集合 [11-100)
     * */
    public static List<Integer> getReportIdList() {
        ArrayList<Integer> result = new ArrayList<>();
        for(ExcelReportEnum anEnum : ExcelReportEnum.values()){
            Integer id = anEnum.getId();
            if (id>=10 && id<100){
                result.add(id);
            }
        }
        return result;
    }
    /**
     * 根据报表id的集合字符串(id之间以,分割),判断是否已经选中报表,返回真实的报表id集合(过滤掉错误的id)
     * */
    public static List<Integer> filterAndGetReportIdList(String reportIdListStr){
        List<Integer> result = new ArrayList<>();
        if (!StringUtils.isBlank(reportIdListStr)){
            return result;
        }
        try {
            List<Integer> reportIdList = Arrays.stream(reportIdListStr.split(","))
                    .map(s -> Integer.valueOf(s))
                    .collect(Collectors.toList());
            result = reportIdList.stream()
                    .filter(s -> getReportIdList().contains(s))
                    .distinct()
                    .collect(Collectors.toList());
        }catch (Exception e){
            return result;
        }
        return  result;
    }

    public Integer getId() {
        return id;
    }
    public String getValue() {
        return value;
    }
    public String getFileType() {
        return fileType;
    }

}
