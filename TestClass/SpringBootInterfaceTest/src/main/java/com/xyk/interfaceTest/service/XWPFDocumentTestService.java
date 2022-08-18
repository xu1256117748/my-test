package com.xyk.interfaceTest.service;

import com.xyk.vo.FileExportVO;

/**
 * @author 徐亚奎
 * @date 2021-10-26 15:51
 * @Description
 */
public interface XWPFDocumentTestService {
    FileExportVO exportWord(String fileAllName, String fileSaveAllPath);
}
