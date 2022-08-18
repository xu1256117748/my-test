package com.xyk.vo.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-11 13:56
 * @Description
 */
@ApiModel("报表导出响应体")
@Data
@Accessors(chain = true)
public class ExcelExportRes {
    @ApiModelProperty("文件下载路径 示例: https://single/hello.xlsx")
    private String fileUrl;
    @ApiModelProperty("文件本地存储路径 示例: https://single/hello.xlsx")
    private String fileLocalSaveUrl;
    @ApiModelProperty("文件全名(含后缀名) 示例: hello.xlsx")
    private String fileAllName;
    @ApiModelProperty("文件名(不含后缀名) 示例: hello")
    private String fileName;
    @ApiModelProperty("文件类型 示例: .xlsx")
    private String fileType;
    @ApiModelProperty("文件大小 示例: 40 KB")
    private String fileSize;
    @ApiModelProperty("备注信息 sheet操作信息会置入此处")
    private final List<String> msgList = new ArrayList<>();
    @ApiModelProperty(value = "workbook对象实体",hidden = true)
    private Workbook workbook;
//    @ApiModelProperty(value = "workbook对象实体",hidden = true)
//    private String lastSubmitTime = ReportExportController.LAST_SUBMIT_TIME;
}
