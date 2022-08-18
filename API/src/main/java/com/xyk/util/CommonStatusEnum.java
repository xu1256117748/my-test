package com.xyk.util;

import lombok.*;

/**
 * @author 徐亚奎
 * @date 2021-08-10 15:49
 */
@AllArgsConstructor
@Getter
public enum CommonStatusEnum {
    FAILURE(000,"执行失败"),
    SUCCESS(200,"执行成功"),
    UNKNOWN(999,"未知异常");


    private Integer code;
    private String msg;
}
