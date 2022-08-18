package JavaBasic.NO_003;

import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-11 15:00
 * @Description
 */
public class Student {
    private  Integer gender;
    String id;
    String name;
    Integer age;

    {
       this.gender = new Random().nextInt(2);
        System.out.println("执行了构造代码块 一");
    }

    public Student(){
        System.out.println("调用了无参构造创建对象");
    }

    {
        System.out.println("执行了构造代码块 二");
    }

    public Student(String id, String name, Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("调用了全参构造创建对象");

    }

    {
        System.out.println("执行了构造代码块 三");
    }

    @Override
    public String toString() {
        return "Student{" +
                "gender=" + gender +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void study(){
        System.out.println("student is studying!");
    }

    public void eat(){
        System.out.println("student is eating!");
    }

    public void sleep(){
        System.out.println("student is sleeping!");
    }

    /**
     * @param time 睡眠时间(单位:秒)
     * @return true/false ture:允许睡觉 false:不允许睡觉 只允许女生睡觉
     * */
    public Boolean sleep(Integer time){
        System.out.println("student is sleeping!");

        return this.gender == 0;
    }




    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
