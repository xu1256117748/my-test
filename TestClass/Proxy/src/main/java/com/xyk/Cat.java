package com.xyk;

import com.xyk.annocation.MyLog;
import com.xyk.pojo.Food;
import org.springframework.stereotype.Component;

/**
 * @author 徐亚奎
 * @date 02/08/2021 09:42
 */
@Component
public class Cat {
    @MyLog(name = "进食",type = "猫科动物")
    public void eat(Food food,String str){
        System.out.println("cat eat");
    }
    @MyLog(name = "睡眠",type = "猫科动物")
    public void sleep(){
        System.out.println("cat sleep");
    }
}
