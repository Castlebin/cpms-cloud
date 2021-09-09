package com.cpms.framework.common.utils;

import com.alibaba.fastjson.JSON;
import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.core.secure.TokenUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

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
public class CsSecureUtil {
    public static TokenUserInfo getUser(){
        TokenUserInfo tokenUserInfo = new TokenUserInfo();
        HttpServletRequest request = CsWebUtil.getRequest();
        if(!Objects.isNull(request)) {
            String header = request.getHeader(CoreCommonConstant.USER_INFO);
            if(StringUtils.isNotBlank(header)){
                try {
                    tokenUserInfo = JSON.parseObject(URLDecoder.decode(header, "UTF-8"), TokenUserInfo.class);
                }catch (UnsupportedEncodingException un) {
                    log.error("token user info parse error",un);
                }
            }
        }
        return tokenUserInfo;
    }

    /**
     *  判断是否是超级管理员（拥有所有权限）
     * @return
     */
    public static boolean isSuperAdmin(){
        TokenUserInfo tokenInfo =getUser();
        return (!CollectionUtils.isEmpty(tokenInfo.getRoleCodes()) && tokenInfo.getRoleCodes().contains(CoreCommonConstant.SUPER_ADMINISTRATOR));
    }

    /**
     *  判断所属租户用户是否是管理员（拥有租户所有权限）
     * @return
     */
    public static boolean isAdmin(){
        TokenUserInfo tokenInfo =getUser();
        return (!CollectionUtils.isEmpty(tokenInfo.getRoleCodes()) && tokenInfo.getRoleCodes().contains(CoreCommonConstant.ADMINISTRATOR));
    }

    /**
     * 获取用户账号
     * @return
     */
    public static String userAccount(){
        return getUser().getUserAccount();
    }
}
