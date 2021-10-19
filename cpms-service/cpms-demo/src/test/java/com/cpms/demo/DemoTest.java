package com.cpms.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: gulang
 * @time: 2021/10/19 9:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    /**
     * jasypt加解密代码
     */
    @Test
    public void jasyptEncrypt(){
        // 默认加密/解密算法是 PBEWithMD5AndDES
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("#p518rcpmsod");// 密钥
        System.out.println(encryptor.encrypt("wq540074./"));
    }
}
