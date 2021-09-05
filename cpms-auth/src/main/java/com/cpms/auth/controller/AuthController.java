package com.cpms.auth.controller;

import com.cpms.auth.authen.IAuthen;
import com.cpms.auth.authen.UserAuthenBuilder;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.secure.AuthInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 授权认证接口
 * @author: gulang
 * @time: 2021/5/24 16:36
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/token")
    public Result<AuthInfo> token(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        IAuthen authenType = UserAuthenBuilder.getGrantType(userLoginDTO.getGrantType());
        AuthInfo authInfo = authenType.authentication(userLoginDTO);
        return ResultUtil.success(authInfo);
    }
}
