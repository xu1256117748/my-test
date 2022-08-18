package com.xyk.interfaceTest.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-22 09:57
 * @Description
 */
@Data
public class StudentInfo {
    private String stuId;
    private String stuName;
    private String attenFlag;
    private ArrayList<String> stateFlag;
    private String createDate;
    private Integer rankGap;

    /**
     * 自定义,用作查询条件
     * */
    private String teacherId;
    private String classId;
    private String subjectCode;
    // 查询最近多久的错题
    private String startTime; // 示例 2021-10-15
    private String endTime; // 示例 2021-10-21
}
