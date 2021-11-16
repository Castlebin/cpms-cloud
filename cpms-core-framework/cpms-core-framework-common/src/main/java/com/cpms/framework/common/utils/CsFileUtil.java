package com.cpms.framework.common.utils;

import com.alibaba.excel.util.FileUtils;
import com.cpms.framework.common.core.domain.FileR;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @description: 文件操作工具类
 * @author: gulang
 * @time: 2021/11/10 17:30
 */
public class CsFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(CsFileUtil.class);
    private static final String OS_NAME = "os.name";
    private static final String OS_TYPE = "win";
    private static String WIN_FILE_UPLOAD_PATH = "D:/cpms/appfiles";

    private static String LINUX_FILE_UPLOAD_PATH = "/cpms/appfiles";
    /**
     * 在yml配置文件自定义保存目录
     */
    private static final String FILE_UPLOAD_PATH_WIN_PROP = "cpms.file-upload-path.win";
    /**
     * 在yml配置文件自定义保存目录
     */
    private static final String FILE_UPLOAD_PATH_LINUX_PROP = "cpms.file-upload-path.linux";


    public static void initUploadPath() {
        String winPath = CsPropsUtil.getString(FILE_UPLOAD_PATH_WIN_PROP);
        if(!CsStringUtil.isEmpty(winPath)) {
            WIN_FILE_UPLOAD_PATH = winPath;
        }
        String linuxPath = CsPropsUtil.getString(FILE_UPLOAD_PATH_LINUX_PROP);
        if(!CsStringUtil.isEmpty(linuxPath)) {
            LINUX_FILE_UPLOAD_PATH = linuxPath;
        }
    }

    public static void downLocalFile(HttpServletResponse response, File file, String fileName){
        OutputStream responseStream = null;
        try {
            if(file == null || !file.exists()){
                throw new BizException(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
            }
            responseStream = response.getOutputStream();
            byte[] resultBytes = FileUtils.readFileToByteArray(file);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/octet-stream");
            String name = fileName;
            String sufix = "";
            if(fileName.contains(CsStringPool.DOT)) {
                name = fileName.substring(0, fileName.lastIndexOf(CsStringPool.DOT));
                sufix = fileName.substring(fileName.lastIndexOf(CsStringPool.DOT));
            }
            name = URLEncoder.encode(Optional.ofNullable(name).orElse(String.valueOf(System.currentTimeMillis())), StandardCharsets.UTF_8.name());
            response.setHeader("Content-disposition", "attachment;filename=" + name + sufix);
            responseStream.write(resultBytes, 0, resultBytes.length);
            responseStream.flush();
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("File Download Exception", e);
            if (null != responseStream) {
                try {
                    responseStream.close();
                } catch (IOException ignored) {
                    logger.error("Close File Stream Exception", e);
                }
            }
        }
    }

    /**
     *
     * @param response
     * @param fileDirName 文件在resource目录的所在目录名称，前后不能加“/”否则解析不到
     * @param fileName 文件名称（包含后缀）
     * @return
     */
    public static void downLocalFile(HttpServletResponse response, String fileDirName,String fileName){
        File file;
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileDirName+File.separator+fileName);
        } catch (FileNotFoundException e) {
            logger.error("File Not Exist Exception", e);
            throw new BizException(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
        }
         downLocalFile(response,file,fileName);
    }

    /**
     *  保存文件到默认目录下
     * @param fileParent 文件归档类型名称 e.g: image、excel
     * @param fileOwner  文件归属者 e.g:用户工号
     * @param file 文件流
     * @return FileR 返回信息封装
     */
    public static FileR saveFileUpload(String fileParent, String fileOwner, MultipartFile file){
        String defaultSaveFilePath = getDefaultSaveFilePath(fileParent, fileOwner);
        return saveFileUpload(defaultSaveFilePath, file);
    }
    /**
     *  保存上传文件
     * @param savePath 绝对路径
     * @param file
     * @return
     */
    public static FileR saveFileUpload(String savePath, MultipartFile file) {
        FileR saveFileR = new FileR();
        if (CsStringUtil.isEmpty(savePath)){
            logger.info("savePath is null");
            saveFileR.setSuccess(false);
            saveFileR.setErrorMessage("savePath is null");
            return saveFileR;
        }
        if(Objects.isNull(file)){
            logger.info("file is null");
            saveFileR.setSuccess(false);
            saveFileR.setErrorMessage("file is null");
            return saveFileR;
        }
        try {
            StringBuilder fileName = new StringBuilder(CsDateUtil.dateFormat(new Date(),CsDateUtil.YYYYMMDDHHMMSSSSS));
            fileName.append((int)((Math.random() * 9+1)*100));
            String originFilename = file.getOriginalFilename();
            if(!CsStringUtil.isEmpty(originFilename)) {
                fileName.append(originFilename);
            }
            String fullPath = savePath+File.separator+ fileName;
            file.transferTo(new File(fullPath));
            saveFileR.setFilePath(savePath);
            saveFileR.setFileFullPath(fullPath);
            saveFileR.setFileName(originFilename);
            return saveFileR;
        } catch (Exception e) {
            logger.error("File Upload Exception", e);
            saveFileR.setSuccess(false);
            saveFileR.setErrorMessage(e.getMessage());
            return saveFileR;
        }
    }

    /**
     * 根据操作系统类型获取对应的默认根目录路径
     * @return
     */
    public static String getDefaultRootPath() {
        initUploadPath();
        String os = System.getProperty(OS_NAME);
        if (os.toLowerCase().startsWith(OS_TYPE)) {
            return WIN_FILE_UPLOAD_PATH;
        } else {
            return LINUX_FILE_UPLOAD_PATH;
        }
    }

    /**
     * 获取默认的保存路径
     * @param fileParent 文件归档类型名称 e.g: image、excel
     * @param fileOwner  文件归属者 e.g:用户工号
     * @return
     */
    public static String getDefaultSaveFilePath(String fileParent,String fileOwner) {
        String defaultRootPath = getDefaultRootPath();
        return createPath(defaultRootPath,fileParent,fileOwner);
    }

    /**
     *  获取Resource目录路径
     * @param fileParent 文件归档类型名称 e.g: image、excel
     * @param fileOwner  文件归属者 e.g:用户工号
     * @return
     */
    public static String getResourceSaveFilePath(String fileParent,String fileOwner) {
        String resourceRootPath = null;
        try {
            resourceRootPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            logger.error("Get Resource Path Exception", e);
        }
        return createPath(resourceRootPath,fileParent,fileOwner);
    }

    /**
     *  创建目录
     * @param rootPath
     * @param parentPath
     * @param fileOwner
     * @return
     */
    private static String createPath(String rootPath, String parentPath,String fileOwner) {
        if(CsStringUtil.isEmpty(rootPath)) {
            rootPath = getDefaultRootPath();
        }
        StringBuilder path = new StringBuilder(rootPath);
        path.append(File.separator);
        path.append(parentPath);
        path.append(File.separator);
        path.append(CsDateUtil.getCurrentYear());
        path.append(File.separator);
        path.append(CsDateUtil.getCurrentMonth());
        path.append(File.separator);
        path.append(CsDateUtil.getCurrentDay());
        path.append(File.separator);
        if(!CsStringUtil.isEmpty(fileOwner)) {
            path.append(fileOwner);
            path.append(File.separator);
        }
        File destFile = new File(path.toString());
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        return path.toString();
    }
}
