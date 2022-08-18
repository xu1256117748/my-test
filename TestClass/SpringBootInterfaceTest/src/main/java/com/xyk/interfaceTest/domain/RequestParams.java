package com.xyk.interfaceTest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 徐亚奎
 * @date 2021-10-21 16:50
 * @Description
 */
@Data
@Accessors(chain = true)
public class RequestParams {
    private Long classId = 1500000100111795393L;
    private Integer subjectId = 01;
    private String timeId = "halfYear";
    private Integer classFlag = 1;
    private Long t = 1634804330413L;
}
