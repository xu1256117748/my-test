package com.xyk;

/**
 * @author 徐亚奎
 * @date 29/07/2021 21:33
 */
public class test {
    public static void main(String[] args) {
        String str = "";
        for(int i = 0; i< 10 ; i++){
            str += "徐浩宇";
        }

        //百度语音已过期
//        StringReader_BaiduAI.outMp3ToDesktopFromTxt("C:\\Users\\12561\\Desktop\\测试文件.txt", "say");

        str = XykApi.FileUtils_GetStrFromFile_Txt("C:\\Users\\12561\\Desktop\\需要朗读的文件.txt");
        System.out.println(str);

        StringReader_System.read(str);

//        StringReader_System.OutMp3ToDesktop(str, "十次徐多多", "E://");

//        StringReader_System.readAndOutToDesktop(str, "十次徐浩宇", null);



    }
}
