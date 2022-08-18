package com.xyk.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 徐亚奎
 * @date 02/08/2021 10:09
 */
@Data
@Accessors(chain = true)
public class Food {
    private String name;
    private Integer num;
}
