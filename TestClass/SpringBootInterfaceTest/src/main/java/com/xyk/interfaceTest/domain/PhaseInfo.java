package com.xyk.interfaceTest.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-28 18:41
 * @Description
 */
@Data
public class PhaseInfo {
    private String phaseName;
    private String phaseCode;
    private List<GradeInfo> grades;
}
