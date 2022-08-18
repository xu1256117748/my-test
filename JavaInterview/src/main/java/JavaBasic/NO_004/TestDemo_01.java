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
    @Test
    public void test2(){
        String FilePathName = "C:\\Users\\12561\\Desktop\\Demo.txt";
        String content = "Hello World!";
        XykApi.writeStrToFile_Txt(FilePathName,content,false,true);
    }

}
