package com.xyk.vo.excel;


import com.xyk.vo.FileExportVO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 徐亚奎
 * @date 2021-10-11 13:56
 * @Description
 */
//@ApiModel("报表导出响应体")
@Data
@Accessors(chain = true)
public class PoiExportVo extends FileExportVO {
//    @ApiModelProperty(value = "workbook对象实体",hidden = true)
    private Workbook workbook;
}
