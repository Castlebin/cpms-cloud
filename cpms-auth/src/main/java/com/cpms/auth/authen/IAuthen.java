package com.cpms.auth.authen;

import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.framework.common.core.secure.AuthInfo;

/**
 * @description: 认证接口类
 * @author: gulang
 * @time: 2021/6/7 19:18
 */
public interface IAuthen {
    AuthInfo authentication(UserLoginDTO userLoginDTO);
}
