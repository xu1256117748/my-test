package JavaBasic.NO_005;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-22 14:49
 * @Description
 */
@Data
public class Student implements Serializable {


    private static final long serialVersionUID = -3050804429503461131L;


    /**
     * 姓名
     * */
    public String name ;
    /**
     * 年龄
     * */
    public Integer age;
    /**
     * 性别 0:女 1:男
     * */
    public Integer gender;
    /**
     * 成绩
     * */
    public Double score;

    public void say(String content){
        System.out.println(name + " say " + content);
    }


}
