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
 * 实现泛型接口--不指定类型
 * */
public class Pig01 implements Animal {
    private String name;
    private Integer age;

    @Override
    public Object eat(Food food) {
        return food;
    }

    @Override
    public Object sleep(Long time) {
        return time;
    }

    @Override
    public Integer grow(Integer age) {
        return age;
    }
}
