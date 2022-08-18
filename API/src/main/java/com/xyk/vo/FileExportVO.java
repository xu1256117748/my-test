package com.xyk.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-11 15:24
 * @Description 文件导出VO层
 */
@Data
@Accessors(chain = true)
public class FileExportVO {
    /**
     * 文件导出路径(服务器端-文件存储路径)
     * */
    private String fileSavePath;
    /**
     * 文件下载路径(客户端-公网下载路径)
     * */
    private String fileDownLoadUrl;
    /**
     * 文件全名 含后缀
     *
     * */
    private String fileAllName;
    /**
     * 文件名(不含后缀)
     * */
    private String fileName;
    /**
     * 文件类型(含.) 示例:.xlsx
     * */
    private String fileType;
    /**
     * 文件大小
     * */
    private String fileSize;
    /**
     * 备注信息
     * */
    private final List<String> msgList = new LinkedList<>();

    public String getFileSavePath() {
        return fileSavePath;
    }

    public FileExportVO setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
        return this;
    }

    public String getFileDownLoadUrl() {
        return fileDownLoadUrl;
    }

    public FileExportVO setFileDownLoadUrl(String fileDownLoadUrl) {
        this.fileDownLoadUrl = fileDownLoadUrl;
        return this;
    }

    public String getFileAllName() {
        return fileAllName;
    }

    public FileExportVO setFileAllName(String fileAllName) {
        this.fileAllName = fileAllName;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileExportVO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public FileExportVO setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getFileSize() {
        return fileSize;
    }

    public FileExportVO setFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public List<String> getMsgList() {
        return msgList;
    }
}
