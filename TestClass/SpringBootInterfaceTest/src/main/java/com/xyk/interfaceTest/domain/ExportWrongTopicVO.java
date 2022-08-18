package com.xyk.interfaceTest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 徐亚奎
 * @date 2021-10-22 16:37
 * @Description
 */
@Data
@Accessors(chain = true)
public class ExportWrongTopicVO {
    public String fileAllName;
    public String fileName;
    public String fileType;
    public String fileExportPath;
}
