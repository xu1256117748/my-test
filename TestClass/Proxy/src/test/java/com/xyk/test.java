package com.xyk;

import com.xyk.pojo.Food;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 徐亚奎
 * @date 02/08/2021 09:21
 */
public class test {
    private Cat cat;
    private Tigger tigger;
    private Tigger2 tigger2;
    @Before
    public void before(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cat = (Cat) context.getBean("cat");
        tigger = (Tigger) context.getBean("tigger");
        tigger2 = (Tigger2) context.getBean("tigger2");
    }

    @Test
    public void testEat(){
        Food food = new Food().setName("猫粮").setNum(2);
        cat.eat(food,"1");
        System.out.println();
        tigger.eat(food,"2");
        System.out.println();
        tigger2.eat(food,"3");
    }
    @Test
    public void testSleep(){
        cat.sleep();
        System.out.println();
        tigger.sleep();
        System.out.println();
        tigger2.sleep();
    }
    @Test
    public void testHunter(){
        tigger.hunter();
        System.out.println();
        tigger2.hunter();
    }
}
