package com.xyk.interfaceTest.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-29 12:56
 * @Description 查询用户班级信息的请求体
 */
@Data
@Accessors(chain = true)
@ApiModel
public class TeachingInfoReq {
    // 学段集合(高中)
    private List<String> phaseList;
    private List<String> gradeIdList;
    private List<String> classIdList;
    private List<String> subjectIdList;
    private List<String> studentIdList;
    // 导出最近多久的错题? 见智学网错题集常量 默认导出最近一周(优先级低于下面两个参数)
    private String recentTime;
    // 导出错题的开始--结束时间 (示例: 2021-3-5 2021-3-11) 如果不指定导出时间,则采用最近多久时间,默认最近多久为最近一周
    private String startTime;
    private String endTime;

    // 查询行政班级还是授课班级 null/0表示查询只授课班级(默认),1表示只查询行政班级,2表示全部查询
    private Integer teachingOrAdministrative = 0;
}
