package com.xyk;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 需要jacob依赖,并要在jdk安装目录下的java/jdk/jre/bin目录下放入.dll文件
 * 下载链接:http://sourceforge.net/project/showfiles.php?group_id=109543&package_id=118368
 * @author 徐亚奎
 * @date 29/07/2021 21:20
 */
public class StringReader_System {
    // 生成文件的音频类型(mp3,wav等音频格式均可)
    public static final String FILE_TYPE_MP3 = ".mp3";
    public static final String FILE_TYPE_WAV = ".wav";

    // 利用计算机自带的朗读--对传入的String串进行朗读
    public static void read(String str){
        // ActiveXComponent类需要在bin目录下放入dll文件才能不报错
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(0));
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(str));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
        System.out.println("--朗读完毕--");
    }
    /**
     * 朗读并在桌面生成音频文件
     * @param str 需要进行阅读的文本
     * @param fileName 生成.mp3文件的文件名(不含后缀,如果文件名为空则会生成随件文件名)
     * @param outPath 文件输出路径(输入不合法时默认为桌面),示例: "C:\\Users\\12561\\Desktop\\"
     * */
    public static void readAndOutToDesktop(String str,String fileName, String outPath){

        fileName = checkFileName(fileName);

        //指定文件音频输出文件位置,默认为桌面
        String outFile = checkOutPath(outPath) + fileName + FILE_TYPE_MP3;

        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");
            //运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-3));
            // 进行朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));
            //下面是构建文件流把生成语音文件
            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();
            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();
            //设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            //设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            //调用输出 文件流打开方法，在指定位置输出一个.wav/.mp3文件
            Dispatch.call(spFileStream, "Open", new Variant(outFile), new Variant(3), new Variant(true));
            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            //设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            //设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            //开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));
            //关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);
            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("***朗读完毕***,***已生成文件***,***["+outFile+"]***");
    }
    /**
     * 不朗读,仅在桌面生成音频文件
     * @param str 需要进行阅读的文本
     * @param fileName 生成.mp3文件的文件名(不含后缀,如果文件名为空则会生成随件文件名)
     * @param outPath 文件输出路径(输入不合法时默认为桌面),示例: "C:\\Users\\12561\\Desktop\\"
     * */
    public static void OutMp3ToDesktop(String str,String fileName, String outPath){

        fileName = checkFileName(fileName);

        //指定文件音频输出文件位置
        String outFile = checkOutPath(outPath) + fileName + FILE_TYPE_MP3;

        ActiveXComponent ax = null;

        try {
            ax = new ActiveXComponent("Sapi.SpVoice");
            //运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            //下面是构建文件流把生成语音文件
            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();
            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();
            //设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            //设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            //调用输出 文件流打开方法，在指定位置输出一个.wav/.mp3文件
            Dispatch.call(spFileStream, "Open", new Variant(outFile), new Variant(3), new Variant(true));
            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            //设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            //设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            //开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));
            //关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);
            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("***朗读完毕***,***已生成文件***,***["+outFile+"]***");
    }

    /**
     * 校验生成的音频文件名的合法性,如果不合法则会生成随机文件名
     * */
    public static String checkFileName(String fileName){
        if (!StringUtils.hasLength(fileName)){
            // 随机生成文件名
            fileName = "输入的文件名为空,生成随机音频文件名:"+UUID.randomUUID();
        }
        return fileName;
    }

    /**
     * 校验生成的.mp3音频文件的路径(不含文件名),默认为桌面,示例:C:\Users\12561\Desktop\
     * */
    public static String checkOutPath(String outPath){
        //指定文件音频输出文件位置,默认为桌面
        if(!StringUtils.hasLength(outPath)){
            outPath = "C:\\Users\\12561\\Desktop\\";
        }else if ( !outPath.endsWith("/") &&  !outPath.endsWith("\\")){
            outPath = outPath + "\\"  ;
        }
        return outPath;
    }

}
