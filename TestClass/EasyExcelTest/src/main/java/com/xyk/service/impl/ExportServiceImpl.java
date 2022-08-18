package com.xyk.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.xyk.entity.Student;
import com.xyk.service.ExportService;
import com.xyk.vo.FileExportVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-11 15:32
 * @Description
 */
@Service
public class ExportServiceImpl implements ExportService {

    @Override
    public FileExportVO exportStudentList(List<Student> studentList) {
        FileExportVO excelVO = new FileExportVO();
        if (CollectionUtils.isEmpty(studentList)){
            excelVO.getMsgList().add("数据源为空");
            return excelVO;
        }
        String path = "E:/single工作区/export/";
        String sheetName = "学生信息";
        String fileName = "学生信息";
        String fileType = ".xlsx";
        String fileAllName = fileName+fileType;
        String filePathAllName =  path+sheetName+fileType;


        ExcelWriterBuilder write = EasyExcel.write(filePathAllName, Student.class);
        // doWrite后会自动关流
        write.sheet(sheetName).doWrite(studentList);
        excelVO.getMsgList().add("导出成功");


        File file = new File(filePathAllName);
        long fileSize = file.length();
        excelVO.getMsgList().add("计算文件大小成功");


        excelVO.setFileName("学生信息")
                .setFileType(".xlsx")
                .setFileSavePath(filePathAllName)
                .setFileSize(fileSize/1024+"KB");
        return excelVO;
    }
}
