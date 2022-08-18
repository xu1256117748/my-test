package com.xyk.interfaceTest.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-28 18:50
 * @Description
 */
@Data
public class TeachingInfo {
    private List<PhaseInfo> administrativeClass;
    private List<PhaseInfo> teachingClass;

    // 行政班
    public static final String ADMINISTRATIVE_CLASS = "administrativeClass";
    // 教学班
    public static final String TEACHING_CLASS = "teachingClass";
}
