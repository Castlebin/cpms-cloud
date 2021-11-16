package com.cpms.framework.common.core.domain;

/**
 * @description: 保存文件返回信息封装
 * @author: gulang
 * @time: 2021/11/16 16:34
 */
public class FileR {
    private boolean success = true;
    private String filePath;
    private String fileFullPath;
    private String fileName;
    private String errorMessage;

    public FileR() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
