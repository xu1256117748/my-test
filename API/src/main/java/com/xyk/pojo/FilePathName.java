package com.xyk.pojo;

/**
 * @author 徐亚奎
 * @date 2021-09-29 13:45
 * @Description
 */
public class FilePathName {
    /**
     * 盘符 示例:C D E F
     * */
    public final static String DRIVER_NAME = "driverName";
    /**
     * 盘符路径 示例:C:\\ C:/
     * */
    public final static String DRIVER_PATH = "driverPath";

    /**
     * 文件夹名 示例:2021/2/5
     * */
    public final static String DIR_NAME = "dirName";
    /**
     * 文件夹路径 示例:2021/2/5/
     * */
    public final static String DIR_PATH = "dirPath";

    /**
     * 文件名(不含后缀) 示例:helloWorld
     * */
    public final static String  FILE_NAME = "fileName";
    /**
     * 文件类型 示例:.xlsx .txt .exe
     * */
    public final static String FILE_TYPE = "fileType";
    /**
     * 文件全名(含后缀) 示例:helloWorld.txt helloWorld.xlsx
     * */
    public final static String FILE_ALL_NAME = "fileAllName";

    /**
     * 文件所在路径 示例:E:/2021/2/21/
     * */
    public final static String FILE_ALL_PATH = "fileAllPath";
    /**
     * 文件全路径名 示例:E:/2021/2/21/helloWorld.txt
     * */
    public final static String FILE_ALL_PATH_NAME = "fileAllPathName";


}
