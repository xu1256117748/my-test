package com.xyk.interfaceTest.controller;

import com.xyk.interfaceTest.service.impl.XWPFDocumentTestServiceImpl;
import com.xyk.vo.JsonResponse;
import com.xyk.vo.FileExportVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 徐亚奎
 * @date 2021-10-26 15:47
 * @Description
 */
@RequestMapping("/export/doc")
@RestController
public class XWPFDocumentTest {
    @Resource
    private XWPFDocumentTestServiceImpl documentTestService;
    @GetMapping("/test")
    public JsonResponse XWPFDocumentTest(){
        String fileAllName = "测试导出.docx";
        String fileSaveAllPath = "E:/single工作区/export/学生错题集/word/"+fileAllName;
        FileExportVO excelVO = documentTestService.exportWord(fileAllName,fileSaveAllPath);
        return JsonResponse.success(excelVO);
    }

}
