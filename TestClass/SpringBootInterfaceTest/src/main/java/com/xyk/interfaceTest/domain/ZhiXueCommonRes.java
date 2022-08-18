package com.xyk.interfaceTest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-22 09:52
 * @Description 请求智学网后,接收智学网响应数据
 */
@Data
@Accessors(chain = true)
public class ZhiXueCommonRes {
    private Integer code;
    private String info;
    private Object result;
}
