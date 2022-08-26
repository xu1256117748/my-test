package JavaBasic.NO_005;

import com.xyk.XykApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-22 14:49
 * @Description
 */
@Slf4j
public class TestDemo_01 {


    /**
     * 生成学生集合,并序列化
     * */
    @Test
    public void test01() throws Exception {

        List<Student> studentList = createStudentList(5);
        System.out.println(studentList);
        FileOutputStream out = new FileOutputStream(new File("D:/序列化对象01.txt"));
        ObjectOutputStream OOS = new ObjectOutputStream(out);
        OOS.writeObject(studentList);
        XykApi.FileUtils_WriteStrToFile_Txt("D:/序列化对象01_实际值.txt",studentList.toString(),null,null);
        // 1000 600
        System.out.println("将对象序列化到文件中完成,目标文件:[D:/序列化对象01.txt],[D:/序列化对象01_实际值.txt]");

    }
    /**
     * 对学生集合进行反序列化 注意:反序列化时,程序的序列号应该与序列化时的序列号保持一致,否则会序列化失败报错
     * */
    @Test
    public void test02()throws Exception{
        FileInputStream in = new FileInputStream(new File("D:/序列化对象01.txt"));
        ObjectInputStream OIS = new ObjectInputStream(in);
        List<Student> studentList = (List<Student>) OIS.readObject();
        System.out.println(studentList);

    }



    private Student createStudent(){
        Student student = new Student();
        student.setAge(new Random().nextInt(10)+15);
        student.setGender(new Random().nextInt(2));
        student.setName("学生 "+new Random().nextInt(10000)+" 号");
        student.setScore(new Double(new Random().nextInt(80)+120)/2);
        return student;
    }

    private List<Student> createStudentList(Integer nums) {
        if ( nums == null || nums <= 0){
            nums = 1;
        }
        List<Student> studentList = new ArrayList<Student>(nums);
        for (int i=0; i < nums; i++){
            studentList.add(createStudent());
        }
        return studentList;

    }


}
