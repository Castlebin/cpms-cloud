package com.cpms.common.utils;

import com.alibaba.fastjson.JSON;
import com.cpms.common.constant.TokenConstant;
import com.cpms.common.core.secure.TokenUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * @description: 用户信息工具类
 * @author: gulang
 * @time: 2021/7/28 19:01
 */
@Slf4j
public class SecureUtil {
    public static TokenUserInfo getUser(){
        TokenUserInfo tokenUserInfo = new TokenUserInfo();
        HttpServletRequest request = WebUtil.getRequest();
        if(Objects.isNull(request)) {
            return tokenUserInfo;
        }
        String header = request.getHeader(TokenConstant.USER_INFO);
        if(StringUtils.isNotBlank(header)){
            try {
                tokenUserInfo = JSON.parseObject(URLDecoder.decode(header, "UTF-8"), TokenUserInfo.class);
            }catch (UnsupportedEncodingException un) {
                log.error("token user info parse error",un);
            }
        }
        return tokenUserInfo;
    }
}
