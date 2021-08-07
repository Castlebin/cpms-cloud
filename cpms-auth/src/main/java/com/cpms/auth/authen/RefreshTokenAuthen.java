package com.cpms.auth.authen;

import com.cpms.auth.common.enums.UserLoginEnum;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.common.core.secure.AuthInfo;
import org.springframework.stereotype.Component;

/**
 * @description: 刷新token授权
 * @author: gulang
 * @time: 2021/6/8 10:25
 */
@Component
public class RefreshTokenAuthen implements IAuthen{
    public static final  String GRANT_TYPE = "refresh_token";
    @Override
    public AuthInfo authentication(UserLoginDTO userLoginDTO) {
        System.out.println("刷新token授权");
        return null;
    }
}
