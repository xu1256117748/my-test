package com.xyk.interfaceTest.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-28 18:39
 * @Description
 */
@Data
public class GradeInfo {
    private String gradeId;
    private String gradeName;
    private Boolean check;
    private List<ClassInfo> classes;
}
