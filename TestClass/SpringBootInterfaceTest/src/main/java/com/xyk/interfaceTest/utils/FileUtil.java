package com.xyk.interfaceTest.utils;

import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author 徐亚奎
 * @date 2021-10-27 14:37
 * @Description
 */
public class FileUtil {
    /**
     * @param htmlContent html代码(包含完成的html,body等结构)
     * @param exportFileAllPathName 导出文件的绝对路径,包含文件名和后缀
     * @return 成功导出则返回导出文件的绝对路径,包含文件名和后缀
     * */
    public static String htmlContentToHtml(String htmlContent, String exportFileAllPathName)  {
        String fileType = ".html";
        if (!checkExportFileType(fileType, exportFileAllPathName)){
           return "导出"+fileType+"类型文件时出错," +
                   "导出文件["+exportFileAllPathName+"]和目标文件类型"+fileType+"不匹配!";
       }
        return export(htmlContent,exportFileAllPathName);
    }
    /**
     * @param str 要写入txt文档的文字 换行符为 "\r\n"
     * @param exportFileAllPathName 导出文件的绝对路径,包含文件名和后缀
     * @return 成功导出则返回导出文件的绝对路径,包含文件名和后缀
     * */
    public static String strToTxt(String str,String exportFileAllPathName)  {
        String fileType = ".txt";
        if (!checkExportFileType(fileType, exportFileAllPathName)){
            return "导出"+fileType+"类型文件时出错," +
                    "导出文件["+exportFileAllPathName+"]和目标文件类型"+fileType+"不匹配!";
        }
        return export(str,exportFileAllPathName);
    }
    /**
     * @param fileType 导出应当为此种文件类型 示例:.xml .txt .docx
     * @param exportFileAllPathName 导出文件全路径
     * */
    private static Boolean checkExportFileType(String fileType , String exportFileAllPathName){
        if (exportFileAllPathName == null || !exportFileAllPathName.endsWith(fileType))
            return false;
        return true;
    }
    /**
     * 导出实现
     * */
    private static String export(String content,String path){
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(content.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 将HTML转成PD格式的文件。html文件的格式比较严格
     * @param htmlFileUrl html文件路径 示例:E:/export/hello.html
     * @param pdfFileUrl 导出pdf文件路径 示例:E:/export/hello.pdf
     * @return 成功导出的pdf文件路径
     */
    // <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
    public static String html2pdf(String htmlFileUrl, String pdfFileUrl) throws Exception {
        // step 1
        String url = new File(htmlFileUrl).toURI().toURL().toString();
        // step 2
        File pdfFile = new File(pdfFileUrl);
        if (!pdfFile.getParentFile().exists()){
            pdfFile.getParentFile().mkdirs();
        }
        OutputStream out = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        // step 3 解决中文支持(另需在html片段的body中添加font字体的css)
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if(Objects.equals("linux",getCurrentOperatingSystem())){
            fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }else{
            fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        return pdfFileUrl;
    }
    /**
     * 获取当前运行设备的操作系统
     * */
    private static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }
}
