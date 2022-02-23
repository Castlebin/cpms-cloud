package com.cpms.lowcode.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @description: 下载工具类
 * @author: gulang
 * @time: 2022/2/15 16:56
 */
@Slf4j
public class CsDownLoadFileUtil {
    /**
     * 生成ZIP
     */
    public static void LoadZipOutputStream(VelocityContext velContext, File file, String fileBaseName, ZipOutputStream zipOutputStream) {
        StringWriter sw = new StringWriter();
        String ENCODED = "UTF-8";
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            Velocity.evaluate(velContext, sw, "logTag" , reader);
            // 添加到zip
            zipOutputStream.putNextEntry(new ZipEntry(fileBaseName));
            IOUtils.write(sw.toString(), zipOutputStream, ENCODED);
            IOUtils.closeQuietly(sw);
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化vm
     */
    public static void initVelocity() {
        Properties properties = new Properties();
        try {
            properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            Velocity.init(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载
     * @param response
     * @param bytes
     * @param zipName
     */
    public static void DownloadGenCode(HttpServletResponse response, byte[] bytes, String zipName) {
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip");
            response.addHeader("Content-Length", "" + bytes.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(bytes, response.getOutputStream());
        } catch (Exception e) {
            log.error("生成压缩包文件系统异常！errMsg:{}", e.getMessage(), e);
        }
    }
}
