package com.cpms.auth.authen;

import com.cpms.auth.common.enums.UserLoginEnum;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.common.core.api.Result;
import com.cpms.common.core.secure.AuthInfo;
import com.cpms.common.core.secure.TokenInfo;
import com.cpms.common.exception.BizException;
import com.cpms.common.utils.JwtUtil;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.api.feign.ISysUserClient;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 系统后台登录认证
 * @author: gulang
 * @time: 2021/6/7 19:43
 */
@Component
public class SysAdminAuthen implements IAuthen{
    @Autowired
    private ISysUserClient sysUserClient;

    public static final String AUTHEN_TYPE = UserLoginEnum.SYS_ADMIN.getName();
    public static final String AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
    @Override
    public AuthInfo authentication(UserLoginDTO userLoginDTO) {
        SysUserLginDTO sysUserLginDTO = new SysUserLginDTO();
        sysUserLginDTO.setUserName(userLoginDTO.getUserName());
        sysUserLginDTO.setPassword(userLoginDTO.getPassword());
        Result<SysUserLoginBO> sysUserLoginBoResult = sysUserClient.sysUserLogin(sysUserLginDTO);
        if(!sysUserLoginBoResult.isSuccess()) {
            throw new BizException(sysUserLoginBoResult);
        }
        SysUserLoginBO sysUserLoginBO = sysUserLoginBoResult.getObj();
        sysUserLoginBO.setAvatar(AVATAR);
        AuthInfo authInfo = new AuthInfo();
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("userName",userLoginDTO.getUserName());
        claims.put("userId","1007");
        claims.put("mobile","15889745718");
        TokenInfo tokenInfo = JwtUtil.createJwt(claims);
        authInfo.setAccessToken(tokenInfo.getToken());
        authInfo.setExpireIn(tokenInfo.getExpire());
        authInfo.setUserInfo(sysUserLoginBO);
        return authInfo;
    }
}
