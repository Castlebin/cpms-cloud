package com.cpms.framework.common.utils;

import com.alibaba.excel.util.FileUtils;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
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
    private static final String CHAR_SPOT = ".";

    public static Result<Void> downLocalFile(HttpServletResponse response, File file, String fileName){
        OutputStream responseStream = null;
        try {
            if(file == null || !file.exists()){
//                 throw new BizException();
                return  ResultUtil.error(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
            }
            responseStream = response.getOutputStream();
            byte[] resultBytes = FileUtils.readFileToByteArray(file);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/octet-stream");
            String name = fileName;
            String sufix = "";
            if(fileName.contains(CHAR_SPOT)) {
                name = fileName.substring(0, fileName.lastIndexOf(CHAR_SPOT));
                sufix = fileName.substring(fileName.lastIndexOf(CHAR_SPOT));
            }
//            int a = 1/0;
            name = URLEncoder.encode(Optional.ofNullable(name).orElse(String.valueOf(System.currentTimeMillis())), StandardCharsets.UTF_8.name());
            response.setHeader("Content-disposition", "attachment;filename=" + name + sufix);

            responseStream.write(resultBytes, 0, resultBytes.length);

            responseStream.flush();
            response.flushBuffer();
        } catch (Exception e) {
            response.setContentType("application/json");
            response.resetBuffer();
            logger.error("File download Exception", e);
            if (null != responseStream) {
                try {
                    responseStream.close();
                } catch (IOException ignored) {
                    logger.error("Close File Stream Exception", e);
                }
            }
            return  ResultUtil.error(GlobalResponseResultEnum.FILE_DOWNLOAD_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     *
     * @param response
     * @param fileDir 文件在resource目录的所在目录名称，前不能加“/”否则解析不到
     * @param fileName 文件名称（包含后缀）
     * @return
     */
    public static Result<Void> downLocalFile(HttpServletResponse response, String fileDir,String fileName){
        File file;
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileDir+File.separator+fileName);
        } catch (FileNotFoundException e) {
            logger.error("File Not Exist Exception", e);
            return  ResultUtil.error(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
        }
        return downLocalFile(response,file,fileName);
    }
}
