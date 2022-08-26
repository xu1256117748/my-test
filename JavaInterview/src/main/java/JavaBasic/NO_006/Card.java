package JavaBasic.NO_006;

import lombok.Data;

import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-22 18:14
 * @Description
 */
@Data
public class Card {
    /**
     * 卡牌名
     * */
    public String name ;
    /**
     * 攻击力
     * */
    public Integer attack;
    /**
     * 防御力
     * */
    public Integer defend;
    /**
     * 卡牌描述
     * */
    public String desc;

    public Card(String name, String desc,Integer attack,Integer defend){
        this.name = name;
        this.desc = desc;
        this.attack = attack;
        this.defend = defend;
    }

    public Card(){
        this.name = "随机卡牌:[NO." + new Random().nextInt(1000)+"]";
        this.attack = new Random().nextInt(101)*50;
        this.defend = new Random().nextInt(101)*50;
        this.desc = "暂无卡牌描述";
    }


    public void say(String content){
        System.out.println(this.name + " say " + content);
    }

    public void say(){
        System.out.println("[当前线程:"+Thread.currentThread().getName()+"]"
                + this.name + " say "
                + String.format("attack: %s,"+"defend: %s,"+"卡牌描述: %s", attack, defend, desc));
    }

}
