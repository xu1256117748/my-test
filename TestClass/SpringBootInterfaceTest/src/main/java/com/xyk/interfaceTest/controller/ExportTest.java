package com.xyk.interfaceTest.controller;

import com.xyk.PoiTool;
import com.xyk.pojo.FilePathName;
import com.xyk.vo.JsonResponse;
import com.xyk.vo.excel.ExcelExportRes;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-10-07 18:17
 * @Description
 */
@RequestMapping("/exportTest")
@RestController
public class ExportTest {

    private PoiTool poiTool = new PoiTool();

    @GetMapping
    public JsonResponse downLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "测试下载";
        Workbook workbook = getWorkbook(null,fileName);

//        ExcelExportRes excelExportRes = poiTool.exportSingleFileToLocal(null, "测试.xlsx", workbook, null);


//        使用下面这种方式需要让controller接口的返回值为void
        if (Objects.isNull(response)){
            return JsonResponse.success("reponse不能为null");
        }
        //清除缓存
        response.reset();
        //设置字符集、文件名、后缀名
        // 导出excel文件，发现下载的excel文件无法正常打开，总是提示文件损坏，需要修复，于是在代码中加入了以下代码
        // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setLocale(new Locale("zh","CN"));
        response.addHeader("Content-Disposition","attachment;filename="
                +new String(fileName.getBytes("utf-8"),"iso-8859-1")+".xlsx");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        workbook.close();
        out.flush();
        out.close();


        return JsonResponse.success(null);
    }
    private Workbook getWorkbook(Workbook wb,String sheetName){
        Workbook workbook = null;
        if (wb == null){
             workbook = new XSSFWorkbook();
        }
        if (sheetName == null){
            sheetName = "sheet";
        }
        CellStyle cellStyle = PoiTool.getCommonCellStyle(workbook);
        Sheet sheet = workbook.createSheet(sheetName);
        Integer maxRowNum = 10;
        Integer maxColNum = 10;
        for (int i = 0;i<maxRowNum;i++){
            Row row = sheet.createRow(i);
            if (i==0){
                for(int j =0; j<maxColNum; j++){
                    Cell cell = row.createCell(j);
                    if (j==0){
                        cell.setCellValue(sheetName);
                        cell.setCellStyle(cellStyle);
                    }
                    if (j==9){
                        sheet.addMergedRegion(new CellRangeAddress(0,0,0,j));
                    }
                }
            }else{
                for(int j =0; j<maxColNum; j++){
                    Cell cell = row.createCell(j);
                    if (j<3){
                        cell.setCellValue(UUID.randomUUID().toString().substring(0,5));
                    }else if (j<6){
                        cell.setCellValue(UUID.randomUUID().toString().substring(0,10));
                    }else {
                        cell.setCellValue(UUID.randomUUID().toString());
                    }

                    cell.setCellStyle(cellStyle);
                }
            }
        }
        // 设置自适应列宽
        for (int j = 0;j<maxColNum;j++){
            // true表示当前列为合并单元格列
            sheet.autoSizeColumn(j,true);
        }
        return workbook;
    }
    private Map<String,String> getParams(){
        String fileName = "测试下载";
        String fileType = ".xlsx";
        String fileAllName = fileName+fileType;
        String fileAllPathName = "E:\\2021\\9\\29\\"+fileAllName;
        HashMap<String, String> map = new HashMap<>(10);

        map.put(FilePathName.FILE_NAME, fileName);
        map.put(FilePathName.FILE_TYPE, fileType);
        map.put(FilePathName.FILE_ALL_NAME, fileAllName);
        map.put(FilePathName.FILE_ALL_PATH_NAME,fileAllPathName);
        return map;

    }
    /**
     *  获取通用单元格样式
     */
    private CellStyle getCommonCellStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 边框线条粗细-四边中型线条
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        // 边框线条颜色-四边全红
        cellStyle.setTopBorderColor(IndexedColors.DARK_RED.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.DARK_RED.getIndex());
        cellStyle.setBottomBorderColor(IndexedColors.DARK_RED.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.DARK_RED.getIndex());

        // 单元格颜色填充-实心绿色
        cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
        cellStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());

        // 字体样式-微软雅黑加粗
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(true);
        return cellStyle;
    }

}
