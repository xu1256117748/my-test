package JavaBasic.NO_004;

import com.xyk.XykApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-18 14:05
 * @Description
 */
@Slf4j
public class TestDemo_01 {
//  1.写到txt文件中
    @Test
    public void test()  {
        String content = "Hello World !";
        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";

        File file = new File(FilePathName);
        // 标志着目标文件是否原本存在(默认值为true)
        Boolean exist = true;

        if (!file.exists()){
            try {
                exist = false;
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter writer = null;
        try {
            out = new FileOutputStream(file,true);// 表示是重写还是追加文本
            osw = new OutputStreamWriter(out);
            writer = new BufferedWriter(osw);
            writer.newLine(); // 换行
            writer.write(content );


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                osw.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        StringBuilder sb = new StringBuilder("");
        if (exist){
            sb.append("目标文件已存在,");
        }else {
            sb.append("目标文件不存在,已创建目标文件,");
        }
        sb.append("目标文件路径为:["+FilePathName+"],");
        sb.append("文本写入目标文件中成功!");
        System.out.println(sb);
    }
    //  1.1 写到txt文件中(代码提取到工具类中)
    @Test
    public void test2(){
        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";
        String content = "Hello World!";
        XykApi.FileUtils_WriteStrToFile_Txt(FilePathName,content,true,true);
    }
    // 2.读取txt文件的文本
    @Test
    public void test3(){
        String filePathAllName = "C:\\Users\\12561\\Desktop\\Demo.txt";

        File file = new File(filePathAllName);
        if (!file.exists()){
            System.out.println("目标文件不存在!");
            return;
        }

        Long length = file.length();
        byte[] bytes = new byte[length.intValue()];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(bytes);
            in.close();
            String gbk = new String(bytes, "gbk");
            System.out.println(gbk);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 2.1 读取txt文件的文本(提取到api中)
    @Test
    public void test4(){
        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";
        String strFromFile_txt = XykApi.FileUtils_GetStrFromFile_Txt(FilePathName);
        System.out.println(strFromFile_txt);
    }
//    3.对文件夹的大小进行递归计算大小
    @Test
    public void test5(){
        // 可以是文件,也可以是文件
//        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";
        String FilePathName = "D:\\SogouInput";

        File file = new File(FilePathName);
        long fileSize = getFileSize(file);

        System.out.println("length:"+fileSize);


    }
    //    3.对文件夹的大小进行递归计算大小(提取到工具类中)
    @Test
    public void test6(){
        // 可以是文件,也可以是文件
//        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";
        String FilePathName = "D:\\SogouInput";
        Long aLong = XykApi.FileUtils_CountFileSize(FilePathName);
        System.out.println(aLong);
    }
































    private long getFileSize(File file) {
        long length = 0;

        if (!file.exists()) return length;

        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files){
                if (f.isDirectory()){

                    length += getFileSize(f);
                }else {
                    length += f.length();
                }

            }
            return length;
        }else {
            return file.length();
        }

    }
}
