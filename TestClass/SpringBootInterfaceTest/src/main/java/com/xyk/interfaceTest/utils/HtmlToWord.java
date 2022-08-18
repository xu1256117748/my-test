package com.xyk.interfaceTest.utils;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.awt.*;
import java.io.*;
import java.util.UUID;

/**
 * @author 徐亚奎
 * @date 2021-10-26 11:35
 * @Description
 */
public class HtmlToWord {
    public static void convert(String htmlContent,String fileSaveAllPath) throws Exception{

        String content=htmlContent;

        byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中

        /*
         * 关键地方
         * 生成word格式 */
        POIFSFileSystem poifs = new POIFSFileSystem();
        poifs.getRoot().createDocument("WordDocument", bais);;
        OutputStream ostream = new FileOutputStream(fileSaveAllPath);

        poifs.writeFilesystem(ostream);  	//写入内容
        bais.close();
        ostream.close();

    }
}
