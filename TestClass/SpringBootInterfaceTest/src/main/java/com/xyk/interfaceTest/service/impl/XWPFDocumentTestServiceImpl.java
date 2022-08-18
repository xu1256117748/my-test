package com.xyk.interfaceTest.service.impl;

import com.xyk.interfaceTest.service.XWPFDocumentTestService;
import com.xyk.vo.FileExportVO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

/**
 * @author 徐亚奎
 * @date 2021-10-26 15:51
 * @Description
 */
@Service
public class XWPFDocumentTestServiceImpl implements XWPFDocumentTestService {
    @Override
    public FileExportVO exportWord(String fileAllName, String fileSaveAllPath) {
        FileExportVO vo = new FileExportVO();
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph p1 = document.createParagraph();
        XWPFRun run = p1.createRun();
        run.setText("hello world!");
        run.setFontFamily("微软雅黑");
        return vo;
    }
}
