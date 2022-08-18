package com.xyk.service;

import com.xyk.vo.excel.ExcelExportRes;
import com.xyk.vo.excel.PoiExportVo;

import java.io.IOException;

/**
 * @author 徐亚奎
 * @date 2021-10-19 11:05
 * @Description
 */
public interface PoiTestService {
    ExcelExportRes colorTest() throws IOException;

    ExcelExportRes borderTest(Integer fillcolorIndex,Integer bordercolorIndex) throws IOException;
}
