import com.xyk.StringReader_BaiduAI;
import com.xyk.StringReader_System;
import com.xyk.XykApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-06 16:17
 * @Description
 */
@Slf4j
public class SystemReaderTest {
    public static final String READ_STR = "";

    @Test
    public void test01(){
        StringBuilder sb = new StringBuilder("");
        for (int i=0;i<10;i++){
            sb.append("HellO World!");
        }

        StringReader_System.read(sb.toString());
    }

    @Test
    public void test02()   {
//        String str = XykApi.getStrFromFile_Txt("C:\\Users\\12561\\Desktop\\新建文本文档 (2).txt");
//        System.out.println(str);

        File file = new File("C:\\Users\\12561\\Desktop\\新建文本文档 (2).txt");
        Long length = file.length();
        byte[] fileContent = new byte[length.intValue()];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(fileContent);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(Arrays.toString(fileContent));
        }

        String content = "";
        try {
            String encoding = "gbk";
            content = new String(fileContent, encoding);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(content);
        }
    }


}
