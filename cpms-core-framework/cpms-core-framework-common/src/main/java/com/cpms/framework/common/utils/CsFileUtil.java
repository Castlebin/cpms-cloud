package com.cpms.framework.common.utils;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

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
    private final Logger logger = LoggerFactory.getLogger(CsFileUtil.class);
    private final String CHAR_SPOT = ".";

    public Result<String> downLocalFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        File file = new File("orderTemplate.xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
        if(!file.exists()){
            return  ResultUtil.error(GlobalResponseResultEnum.FILE_NOT_EXIST_ERROR);
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentLength((int) file.length());
        String name = fileName;
        String sufix = "";
        if(fileName.contains(CHAR_SPOT)) {
            name = fileName.substring(0, fileName.lastIndexOf(CHAR_SPOT));
            sufix = fileName.substring(fileName.lastIndexOf(CHAR_SPOT));
        }
        name = URLEncoder.encode(Optional.ofNullable(name).orElse(String.valueOf(System.currentTimeMillis())), StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + name + sufix);

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            logger.error("File download Exception",e);
            return  ResultUtil.error(GlobalResponseResultEnum.FILE_DOWNLOAD_ERROR);
        }
        return ResultUtil.success();
    }

    public static void main(String[] args) {
        String name = null;
        String s = Optional.ofNullable(name).orElse(String.valueOf(System.currentTimeMillis()));

        System.out.println(s);
    }
}
