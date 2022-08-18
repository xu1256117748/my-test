package com.xyk.common;

import com.xyk.pojo.Food;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 徐亚奎
 * @date 2021-09-06 16:26
 * @Description
 */
@Data
@Accessors(chain = true)
/**
 * 实现泛型接口--指定类型
 * */
public class Pig02 implements Animal<Boolean> {
    private String name;
    private Integer age;


    @Override
    public Boolean eat(Food food) {
        return true;
    }

    @Override
    public Boolean sleep(Long time) {
        return true;
    }

    @Override
    public Integer grow(Integer age) {
        return age;
    }
}
