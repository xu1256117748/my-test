package com.xyk;


import com.xyk.constant.CommonConstant;
import com.xyk.constant.ExcelReportEnum;
import com.xyk.vo.excel.ExcelExportRes;
import io.jsonwebtoken.lang.Strings;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * @author 徐亚奎
 * @date 2021-10-13 10:42
 * @Description
 */
@Component
public class PoiTool {

//    @Autowired
//    @Lazy
    private CommonConstant commonConstant;

    private static final int BUFFER_SIZE = 2 * 1024;
    private static final String DEFAULT_FILE_TYPE = ExcelReportEnum.XLS.getFileType();
    /**
     * 用于判断是 本地运行 / 服务器运行
     * */
    private static Integer testOrOpen =
            "linux".equals(System.getProperty("os.name")) ? 2 : 1 ; // 1:本地测试 2:linux服务器部署
    /**
     * zip文件导出时的默认文件名
     * */
    private static final String DEFAULT_MULTI_EXPORT_NAME = "一键导出.zip";


    /**
     * 单文件导出到本地,并封装/返回文件信息到 导出响应体对象
     * @param fileLocalSavePath 文件路径,传入为null则为默认存储路径 示例: c:/export/学科成绩分析.xlsx  /data/static/upload/uuid/学科成绩分析.xlsx
     * @param fileAllName 文件全名,传入为null时使用uuid文件名 操作中会拼接(uuid)以保证文件唯一性  示例: 学科成绩分析.xlsx
     * @param workbook 工作簿对象,用于写入本地文件
     * @param excelExportRes 导出报表的响应体对象,用于封装导出文件的信息
     * */
    public ExcelExportRes exportSingleFileToLocal(String fileLocalSavePath, String fileAllName, Workbook workbook, ExcelExportRes excelExportRes) throws IOException {
        // 本地存储文件夹
        String localSavaDir = testOrOpen == 1  ? "E:/single工作区/export/" : commonConstant.getServicePath();
        // 网络下载文件夹url
        String downLoadDir = testOrOpen == 1  ? "E:/single工作区/export/" : commonConstant.getServiceUrl();

        // 文件名为空时,则生成 随机uuid文件名
        fileAllName = StringUtils.isBlank(fileAllName) ?
                UUID.randomUUID().toString().replace("-","")+ DEFAULT_FILE_TYPE : fileAllName;
        // 为了保证文件名的唯一,防止文件覆盖,在文件名和文件类型之间生成随机uuid
        fileAllName = fileAllName.substring(0,fileAllName.lastIndexOf('.'))
                +"("+UUID.randomUUID().toString().replace("-","")+")"
                +fileAllName.substring(fileAllName.lastIndexOf('.'));
        String fileName = fileAllName.substring(0,fileAllName.lastIndexOf('.'));
        String fileType = fileAllName.substring(fileAllName.lastIndexOf('.'));

        // 文件存储路径为空时则为 默认存储路径
        fileLocalSavePath = StringUtils.isBlank(fileLocalSavePath) ?
                 localSavaDir + fileAllName : fileLocalSavePath;
        // 响应体对象为空时,则创建响应体对象
        excelExportRes = excelExportRes == null ? new ExcelExportRes() : excelExportRes;
        excelExportRes.setFileName(fileName)
                .setFileType(fileType)
                .setFileAllName(fileAllName);
        // 写入本地的本地存储路径中
        File file = new File(fileLocalSavePath);
            // 不存在文件夹路径则创建文件夹
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        workbook.close();
        out.close();
        // 计算文件大小并封装
        excelExportRes.setFileSize(getFileSize(new File(fileLocalSavePath).length()));
        // 封装下载路径
        excelExportRes.setFileUrl(downLoadDir+fileAllName);
        // 封装本地存储路径
        excelExportRes.setFileLocalSaveUrl(fileLocalSavePath);
        return excelExportRes;
    }

    /**
     * 把多个文件压缩,存储到本地,返回下载路径
     * @param fileList 根据文件路径/url创建的file文件集合
     * @param zipSaveAllPath zip文件存储的全路径名 示例:E:/downLoad/一键导出.zip  /data/static/一键导出.zip
     * @param zipAllName zip文件全名,传入为null时适用默认文件名 操作中会拼接(uuid)以保证文件唯一性 示例: 一键导出.zip
     * @param excelExportRes 报表导出的响应体,用来封装导出时产生的数据
     * */
    public ExcelExportRes exportZipToLocal(List<File> fileList, String zipSaveAllPath, String zipAllName, ExcelExportRes excelExportRes) throws FileNotFoundException {
        // 本地存储文件夹
        String localSavaDir = testOrOpen == 1  ? "E:/single工作区/export/" : commonConstant.getServicePath();
        // 网络下载文件夹url
        String downLoadDir = testOrOpen == 1  ? "E:/single工作区/export/" : commonConstant.getServiceUrl();

        // 创建响应体对象
        excelExportRes = excelExportRes == null ? new ExcelExportRes() : excelExportRes;
        if (CollectionUtils.isEmpty(fileList)){
            return excelExportRes;
        }
        // 参数格式化
        zipAllName = Strings.hasLength(zipAllName) ? zipAllName : DEFAULT_MULTI_EXPORT_NAME;
        zipAllName = zipAllName.substring(0,zipAllName.lastIndexOf('.'))
                    + "("+UUID.randomUUID().toString().replace("-","")+")"
                    + zipAllName.substring(zipAllName.lastIndexOf('.'));
        // zip的全 本地存储路径
        zipSaveAllPath = Strings.hasLength(zipSaveAllPath) ? zipSaveAllPath : localSavaDir + zipAllName;
        // zip的全 网络下载路径
        String zipDownLoadUrl = downLoadDir + zipAllName;
        // 封装部分响应体信息
        excelExportRes.setFileAllName(zipAllName)
                        .setFileName(zipAllName.substring(0,zipAllName.lastIndexOf('.')))
                        .setFileType(zipAllName.substring(zipAllName.lastIndexOf('.')))
                        .setFileUrl(zipDownLoadUrl)
                        .setFileLocalSaveUrl(zipSaveAllPath);
        excelExportRes.getMsgList().add("成功导出单文件数量:"+fileList.size()+",即将对此"+fileList.size()+"个文件进行压缩!");
        // zip导出流 zipFile 压缩文件对象
        File zipFile = new File(zipSaveAllPath);
        if (!zipFile.getParentFile().exists()){
            zipFile.getParentFile().mkdirs();
        }
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i =0;i<fileList.size();i++) {
                excelExportRes.getMsgList().add("即将对第"+(i+1)+"个文件进行压缩!");
                // file 即将被压缩的文件
                File file = fileList.get(i);
                FileInputStream in = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                //解决Linux下，中文文件名乱码
                zipEntry.setUnixMode(644);
                zos.putNextEntry(zipEntry);
                byte[] buf = new byte[BUFFER_SIZE];
                int len;
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
                excelExportRes.getMsgList().add("压缩第"+(i+1)+"个文件成功!");
            }
        } catch (Exception e) {
            excelExportRes.getMsgList().add("压缩文件时出错:"+e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    excelExportRes.getMsgList().add("压缩文件关流时出错:"+e);
                }
            }
        }
        excelExportRes.getMsgList().add("将压缩文件写入本地成功!");
        // zip文件大小
        excelExportRes.setFileSize(getFileSize(new File(zipSaveAllPath).length()));
        return excelExportRes;
    }



    /**
     * 将long类型的大小转换为 xxx KB的形式
     * @param length 文件大小
     * */
    private static String getFileSize(Long length){
        if (length<1024){
            return length + " B";
        }else if (length < 1024*1024){
            return length/1024 +" KB";
        }else if (length < 1024*1024*1024){
            return length/1024/1024 +" MB";
        }else{
            return length/1024/1024/1024 + " GB";
        }
    }


    /**
     *  获取通用单元格样式(水平/垂直居中,自动换行,四边中型黑色线条,微软雅黑字体)
     * @param workbook 工作簿对象
     * @return 通用单元格样式
     */
    public static CellStyle getCommonCellStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);

        // 边框线条粗细-四边中型线条
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);

        // 边框线条颜色-四边全黑
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        // 字体样式-微软雅黑加粗
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(false);
        cellStyle.setFont(font);

        return cellStyle;
    }
    /**
     *  获取自定义单元格样式(默认:水平/垂直居中,自动换行,微软雅黑,无边框)
     * @param workbook 工作簿对象
     * @param backgroundColorParams 填充颜色参数
     *                              (1.填充颜色 backgroundColorParams.getColorIndex()
     *                              2.填充样式 backgroundColorParams.getFillPatternType())
     * @return 自定义的单元格样式
     */
    public static CellStyle getCellStyle(Workbook workbook, BackgroundColorParams backgroundColorParams){
        if (backgroundColorParams == null){
            backgroundColorParams = new BackgroundColorParams();
        }
        CellStyle cellStyle = workbook.createCellStyle();
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);
        // 设置填充颜色
        cellStyle.setFillForegroundColor(backgroundColorParams.getColorIndex().shortValue());
        // 设置填充样式
        cellStyle.setFillPattern(backgroundColorParams.getFillPatternType());
        // 字体样式-微软雅黑加粗
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(false);
        cellStyle.setFont(font);

        return cellStyle;
    }
    /**
     * 获取自定义单元格样式(默认水平居中,自动换行)
     * @param workbook 工作簿对象
     * @param backgroundColorParams 单元格填充色的参数(颜色和填充类型)
     *                       null:无填充色 else:指定填充色和填充类型(默认为浅灰色/实心填充)
     * @param borderParams 边框参数
     *                     null:无边框 else:按照指定的边框参数生成边框(默认四边框,黑色,中型粗细)
     * @param fontParams 字体参数,
     *                     null:默认微软雅黑,不加粗 else:按照指定字体参数生成租字体(默认微软雅黑,不加粗)
     * */
    public static CellStyle getCellStyle(Workbook workbook, BackgroundColorParams backgroundColorParams, BorderParams borderParams, FontParams fontParams) {
        if (workbook == null){
            return null;
        }
        CellStyle cellStyle = workbook.createCellStyle();
        if (backgroundColorParams != null){
            // 单元格颜色填充-实心
            cellStyle.setFillPattern(backgroundColorParams.fillPatternType);
            cellStyle.setFillForegroundColor(backgroundColorParams.colorIndex.shortValue());
        }
        if (borderParams != null){
            if (borderParams.topLine!=null && borderParams.topLine){
                cellStyle.setTopBorderColor(borderParams.topLineColorIndex.shortValue()); // 上边框颜色
                cellStyle.setBorderTop(borderParams.topLineThickness); // 上边框粗细
            }
            if (borderParams.rightLine!=null && borderParams.rightLine){
                cellStyle.setRightBorderColor(borderParams.rightLineColorIndex.shortValue()); // 右边框颜色
                cellStyle.setBorderRight(borderParams.rightLineThickness); // 右边框粗细
            }
            if (borderParams.bottomLine!=null && borderParams.bottomLine){
                cellStyle.setBottomBorderColor(borderParams.bottomLineColorIndex.shortValue()); // 下边框颜色
                cellStyle.setBorderBottom(borderParams.bottomLineThickness); // 下边框粗细
            }
            if (borderParams.leftLine!=null && borderParams.leftLine){
                cellStyle.setLeftBorderColor(borderParams.leftLineColorIndex.shortValue()); // 左边框颜色
                cellStyle.setBorderLeft(borderParams.leftLineThickness); // 左边框粗细
            }
        }
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);

        // 字体样式-默认微软雅黑
        fontParams =  fontParams == null ? new FontParams() : fontParams;
        Font font = workbook.createFont();
        font.setFontName(fontParams.fontName);
        font.setBold(fontParams.bold);
        cellStyle.setFont(font);

        return cellStyle;
    }
    /**
     * 给单元格添加批注 需要强制换行的文本为传入富文本RichTextString对象,换行以\r\n为分隔符 示例:"我是\r\n注释!"
     * @param cell 需要添加注释的单元格
     * @param commentStr 注释字符串 支持/n强制换行
     * @param width 宽度 横跨x个单元格
     *              null/0: 默认横跨2单元格
     * @param high 高度 横跨x个单元格
     *             null/0: 默认横跨3单元格
     * */
    public static void addComment(Cell cell, Object commentStr, Integer width, Integer high) {
        if (cell == null){
            return;
        }
        Sheet sheet = cell.getSheet();
        Workbook workbook = sheet.getWorkbook();
        Integer rowIndex = cell.getRowIndex();
        Integer columnIndex = cell.getColumnIndex();

        if (Objects.isNull(commentStr)){
            commentStr = "";
        }

        // 格式化参数
        if (!(commentStr instanceof RichTextString)){
            String value = String.valueOf(commentStr);
            // 使用富文本,以实现文本的强制换行
            commentStr = workbook instanceof HSSFWorkbook ?
                    new HSSFRichTextString(value) : new XSSFRichTextString(value);
        }
        width = Objects.isNull(width) || width == 0 ? 2 : width;
        high = Objects.isNull(high) || high == 0 ? 3 : high;
        // 清除当前注释
        cell.removeCellComment();

        // HSSFClientAnchor用于创建一个新的端锚，并设置锚的左下和右下坐标,用于图片插入，画线等操作。
        // dx1 dy1 起始单元格中的x,y坐标.
        // dx2 dy2 结束单元格中的x,y坐标
        // col1,row1 指定起始的单元格，下标从0开始
        // col2,row2 指定结束的单元格 ，下标从0开始
        ClientAnchor anchor = workbook instanceof HSSFWorkbook ?
                new HSSFClientAnchor(0,0,0,0,
                        columnIndex.shortValue(),rowIndex,
                        (short)(columnIndex+width),rowIndex+high) :
                new XSSFClientAnchor(0,0,0,0,
                        columnIndex,rowIndex,
                        columnIndex+width,rowIndex+high);
        // 创建HSSFPatriarch绘图对象,HSSFPatriarch是所有注释的容器
        Drawing drawing = sheet.createDrawingPatriarch();
        // 在注释容器中添加注释
        Comment comment = drawing.createCellComment(anchor);
        // 输入批注信息
        comment.setString((RichTextString) commentStr);
        // 将批注添加到单元格对象中
        cell.setCellComment(comment);
    }
    /**
     * 压缩成ZIP 方法1
     * @param srcDir 压缩文件夹路径
     * @param out    压缩文件输出流
     * @param keepDirStructure  是否保留原来的目录结构,
     *                          true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     *@throws RuntimeException 压缩失败会抛出运行时异常
     * */
    public static void dirToZip(String srcDir, OutputStream out, boolean keepDirStructure) throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),keepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }

    /**
     * 边框参数
     * */
    @Data
    @Accessors(chain = true)
    public static class BorderParams {
        Boolean topLine = true; // 上边框:存在
        Boolean rightLine = true; // 右边框:存在
        Boolean bottomLine = true; // 下边框:存在
        Boolean leftLine = true; // 左边框:存在
        Integer lineColorIndex = 0; //四条边框颜色
        Integer topLineColorIndex = 0; // 上边框:黑色
        Integer rightLineColorIndex = 0; // 右边框:黑色
        Integer bottomLineColorIndex = 0; // 下边框:黑色
        Integer leftLineColorIndex = 0; // 左边框:黑色
        BorderStyle topLineThickness  = BorderStyle.MEDIUM; // 上边框粗细:中型
        BorderStyle rightLineThickness  = BorderStyle.MEDIUM; // 右边框粗细:中型
        BorderStyle bottomLineThickness  = BorderStyle.MEDIUM; // 下边框粗细:中型
        BorderStyle leftLineThickness  = BorderStyle.MEDIUM; // 左边框粗细:中型
        public BorderParams setLineColorIndex(Integer lineColorIndex){
            topLineColorIndex= lineColorIndex;
            rightLineColorIndex = lineColorIndex;
            bottomLineColorIndex= lineColorIndex;
            leftLineColorIndex=lineColorIndex;
            return this;
        }
    }
    /**
     * 填充颜色参数
     */
    @Data
    @Accessors(chain = true)
    public static class BackgroundColorParams {
        Integer colorIndex = 67; //默认浅灰色
        FillPatternType fillPatternType = FillPatternType.SOLID_FOREGROUND; //默认实心填充
    }
    /**
     * 字体参数
     */
    @Data
    @Accessors(chain = true)
    public static class FontParams{
        String fontName = "微软雅黑"; // 字体
        Boolean bold = false; // 是否加粗
    }
}

