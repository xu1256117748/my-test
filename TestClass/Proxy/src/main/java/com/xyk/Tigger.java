package com.xyk;

import com.xyk.annocation.MyLog;
import org.springframework.stereotype.Component;

/**
 * @author 徐亚奎
 * @date 23/07/2021 01:08
 */
@Component
class Tigger extends Cat {

    @MyLog(type = "老虎",name = "捕猎")
    public void hunter(){
        System.out.println("tigger hunter");
    }
}