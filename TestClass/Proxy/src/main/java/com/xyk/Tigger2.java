package com.xyk;

import com.xyk.annocation.MyLog;
import com.xyk.pojo.Food;
import org.springframework.stereotype.Component;

/**
 * @author 徐亚奎
 * @date 02/08/2021 10:02
 */
@Component
public class Tigger2 extends Cat {

    @Override
    @MyLog(name = "进食",type = "老虎2")
    public void eat(Food food,String str){
        System.out.println("tigger2 eat");
    }

    @Override
    @MyLog(name = "睡眠",type = "老虎2")
    public void sleep(){
        System.out.println("tigger2 sleep");
    }

    @MyLog(name = "捕猎",type = "老虎2")
    public void hunter(){
        System.out.println("tigger2 hunter");
    }

}
