package com.xyk.service.impl;

import com.xyk.PoiTool;
import com.xyk.service.PoiTestService;
import com.xyk.vo.excel.ExcelExportRes;
import com.xyk.vo.excel.PoiExportVo;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

/**
 * @author 徐亚奎
 * @date 2021-10-19 11:05
 * @Description
 */
@Service
public class PoiTestServiceImpl implements PoiTestService {
    private PoiTool poiTool;

    @Override
    public ExcelExportRes colorTest() throws IOException {
        ExcelExportRes res = new ExcelExportRes();
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetName = "poi样式-颜色&填充样式";
        XSSFSheet sheet = workbook.createSheet(sheetName);
        Integer maxRowNum =  1 + 3 +80 -1;
        Integer maxColNum = FillPatternType.values().length + 1 - 1;
        CellStyle commonCellStyle = PoiTool.getCommonCellStyle(workbook);
        for (int i = 0; i <=maxRowNum ; i++){
            XSSFRow row = sheet.createRow(i);
            // 创建标题行
            if (i==0){
                for(int j =0;j<=maxColNum; j++){
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(sheetName);
                    cell.setCellStyle(commonCellStyle);
                }
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,maxColNum));
            }
            // 菜单
            if (i==1){
                for (int j = 0; j <=maxColNum; j++){
                    XSSFCell cell = row.createCell(j);
                    if (j==0){
                        cell.setCellValue("颜色对应的short值");
                        cell.setCellStyle(commonCellStyle);
                    }else{
                        cell.setCellValue("填充样式");
                        cell.setCellStyle(commonCellStyle);
                    }
                }
                sheet.addMergedRegion(new CellRangeAddress(1,1,1,maxColNum));
            }
            // 菜单
            if (i==2){
                for (int j = 0; j <=maxColNum; j++){
                    XSSFCell cell = row.createCell(j);
                    if (j==0){
                        cell.setCellValue("颜色对应的short值");
                        cell.setCellStyle(commonCellStyle);
                    }else{
                        short code = FillPatternType.values()[j - 1].getCode();
                        cell.setCellValue(code);
                        cell.setCellStyle(commonCellStyle);
                    }
                }
            }
            // 菜单
            if (i==3){
                for (int j = 0; j <=maxColNum; j++){
                    XSSFCell cell = row.createCell(j);
                    if (j==0){
                        cell.setCellValue("颜色对应的short值");
                        cell.setCellStyle(commonCellStyle);
                    }else{
                        String value = FillPatternType.values()[j - 1].toString();
                        cell.setCellValue(value);
                        cell.setCellStyle(commonCellStyle);
                        String comment = "对应填充模式代码:\r\n"+"cellStyle.setFillPattern(FillPatternType."+value+");";
                        RichTextString commentStr = workbook instanceof XSSFWorkbook ?
                                new XSSFRichTextString(comment) : new HSSFRichTextString(comment);
                        Font font = workbook.createFont();
                        font.setColor(IndexedColors.RED.getIndex());
                        commentStr.applyFont(comment.lastIndexOf('(')+16+1,comment.lastIndexOf(')'), font);
                        PoiTool.addComment(cell,commentStr,null,null);
                    }
                }
                sheet.addMergedRegion(new CellRangeAddress(1,3,0,0));
            }
            // 数据写入
            if (i>3){
                Integer colorIndex = i-4;
                row.setHeight((short) (256*3));
                for (int j = 0; j <= maxColNum; j++){
                    XSSFCell cell = row.createCell(j);
                    if (j==0){
                        cell.setCellValue(colorIndex);
                        cell.setCellStyle(commonCellStyle);
                        String comment = "对应颜色代码:\r\n"+"cellStyle.setFillForegroundColor("+colorIndex+");";
                        RichTextString commentStr = workbook instanceof XSSFWorkbook ?
                                new XSSFRichTextString(comment) : new HSSFRichTextString(comment);
                        Font font = workbook.createFont();
                        font.setColor(IndexedColors.RED.getIndex());
                        commentStr.applyFont(comment.lastIndexOf('(')+1,comment.lastIndexOf(')'), font);
                        PoiTool.addComment(cell,commentStr,null,null);
                    }else if (j>0){
                        cell.setCellValue("");
                        cell.setCellStyle(PoiTool.getCellStyle(workbook,
                                new PoiTool.BackgroundColorParams()
                                        .setColorIndex(colorIndex)
                                        .setFillPatternType(
                                                FillPatternType.forInt(j-1))));
                    }
                }
                System.out.println("第"+(i-1)+"行数据写入成功!");
            }
        }
        return poiTool.exportSingleFileToLocal(null, "poi样式.xlsx", workbook, res);
    }

    @Override
    public ExcelExportRes borderTest(Integer fillColorIndex,Integer borderColorIndex) throws IOException {
        ExcelExportRes vo = new ExcelExportRes();
        Workbook workbook = new XSSFWorkbook();
        String sheetName = "poi样式-边框颜色&粗细&边框模式";
        Sheet sheet = workbook.createSheet(sheetName);
        Integer maxRowNum = 10 - 1;
        Integer maxColNum = 10 - 1;
        // 标题的单元格样式
        CellStyle titleCellStyle = poiTool.getCellStyle(workbook,new PoiTool.BackgroundColorParams().setColorIndex(fillColorIndex),
                null,null);
        // 菜单的单元格样式
        CellStyle menuCellStyle = poiTool.getCellStyle(workbook,new PoiTool.BackgroundColorParams().setColorIndex(fillColorIndex),
                null,null);

        // 数据区的单元格样式
        CellStyle dataCellStyle = poiTool.getCellStyle(workbook,new PoiTool.BackgroundColorParams().setColorIndex(fillColorIndex),
                null,null);

        for(int i = 0; i<=maxRowNum; i++){
            Row row = sheet.createRow(i);
            if (i==0){
                for(int j =0;j<=maxColNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(sheetName);
                    cell.setCellStyle(titleCellStyle);
                }
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,maxColNum));
            }
            if (i==1){
                for(int j =0;j<=maxColNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue("一级菜单");
                    cell.setCellStyle(menuCellStyle);
                }
            }
            if (i==2){
                for(int j =0;j<=maxColNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue("二级菜单");
                    cell.setCellStyle(menuCellStyle);
                }
            }
            if (i==3){
                for(int j =0;j<=maxColNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue("三级菜单");
                    cell.setCellStyle(menuCellStyle);
                }
            }
            if (i>3){
                for(int j =0;j<=maxColNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(new Random().nextInt(100));
                    cell.setCellStyle(dataCellStyle);
                }
            }
        }



        return poiTool.exportSingleFileToLocal(null, "poi样式-边框颜色&粗细&边框模式.xlsx", workbook, vo);
    }


}
