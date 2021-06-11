package com.cpms.auth.authen;

import com.cpms.auth.common.enums.UserLoginEnum;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.common.core.secure.AuthInfo;
import com.cpms.common.core.secure.TokenInfo;
import com.cpms.common.utils.JwtUtil;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 系统后台登录认证
 * @author: gulang
 * @time: 2021/6/7 19:43
 */
@Component
public class SysAdminAuthen implements IAuthen{
    public static final String AUTHEN_TYPE = UserLoginEnum.SYS_ADMIN.getName();
    public static final String AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
    @Override
    public AuthInfo authentication(UserLoginDTO userLoginDTO) {
        System.out.println("系统后台登录认证");
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("userName",userLoginDTO.getUserName());
        claims.put("userId","1007");
        claims.put("mobile","15889745718");
        TokenInfo tokenInfo = JwtUtil.createJwt(claims);
        AuthInfo authInfo = new AuthInfo();
        SysUserLoginBO sysUserLoginBO = new SysUserLoginBO();
        sysUserLoginBO.setAccount("sysSuperAdmin");
        sysUserLoginBO.setPermissions(Lists.newArrayList("add","delete","edit"));
        sysUserLoginBO.setRoles(Lists.newArrayList("admin","market"));
        sysUserLoginBO.setAvatar(AVATAR);
        authInfo.setAccessToken(tokenInfo.getToken());
        authInfo.setExpireIn(tokenInfo.getExpire());
        authInfo.setUserInfo(sysUserLoginBO);
        return authInfo;
    }
}
