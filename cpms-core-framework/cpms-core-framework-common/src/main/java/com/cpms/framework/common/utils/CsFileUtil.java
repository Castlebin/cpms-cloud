package com.cpms.framework.common.utils;

import com.alibaba.excel.util.FileUtils;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @description: 文件操作工具类
 * @author: gulang
 * @time: 2021/11/10 17:30
 */
public class CsFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(CsFileUtil.class);
    private static final String OS_NAME = "os.name";

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
     * @param fileDir 文件在resource目录的所在目录名称，前后不能加“/”否则解析不到
     * @param fileName 文件名称（包含后缀）
     * @return
     */
    public static void downLocalFile(HttpServletResponse response, String fileDir,String fileName){
        File file;
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileDir+File.separator+fileName);
        } catch (FileNotFoundException e) {
            logger.error("File Not Exist Exception", e);
            throw new BizException(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
        }
         downLocalFile(response,file,fileName);
    }


    public  static boolean saveFileUpload(String savePath) {
        return true;
    }
}
