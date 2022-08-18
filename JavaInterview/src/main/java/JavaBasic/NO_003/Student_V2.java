package JavaBasic.NO_003;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-11 15:13
 * @Description
 */
public class Student_V2 extends Student{
    @Override
    public void eat(){
        System.out.println("Student_V2 is eating!");
    }

    public void say(){
        System.out.println("我是Student_V2的扩展方法:say");
    }

    /**
     * @param time 睡眠时间(单位:秒)
     * @return true/false 是否允许睡觉 true:允许,false:不许云 只允许男生睡觉
     * */
    @Override
    public Boolean sleep(Integer time){

        return this.getGender() == 1;
    }




//    @Override
//    public String toString() {
//        return "Student_V2{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
}
