package com.xyk.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-24 16:07
 * @Description
 */
@Data
@Accessors(chain = true)
public class Cat {
    /**
     * id
     * */
    public Long id;
    public String name;
    public Integer age;
    public Boolean alive;
    private List children;
}
