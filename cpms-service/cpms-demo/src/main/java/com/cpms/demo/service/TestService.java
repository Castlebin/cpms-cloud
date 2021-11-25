package com.cpms.demo.service;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: gulang
 * @time: 2021/11/25 17:53
 */
@Component("cpmsTest")
public class TestService {
    public boolean hasPermission(String permission){
        return false;
    }
}
