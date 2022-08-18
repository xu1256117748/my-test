package com.xyk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @author 徐亚奎
 * @date 2021-08-10 10:27
 */
@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {
    private Integer code;
    private Object data;
    private String msg;

    public static JsonResponse success(Object data){
        return new JsonResponse(200,data,null);
    }
    public static JsonResponse fail(Object data){
        return new JsonResponse(201,data,null);
    }
}
