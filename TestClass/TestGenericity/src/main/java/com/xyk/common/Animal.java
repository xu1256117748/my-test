package com.xyk.common;

import com.xyk.pojo.Food;

/**
 * @author 徐亚奎
 * @date 2021-09-06 16:23
 * @Description
 */
/**
 * 泛型接口
 * */
public interface Animal <T> {

    T eat(Food food);
    T sleep(Long time);
    Integer grow(Integer age);
}
