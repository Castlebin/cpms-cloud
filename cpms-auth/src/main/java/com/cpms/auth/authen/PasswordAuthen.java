package com.cpms.auth.authen;


import com.cpms.auth.common.constants.RedisKeyConstant;
import com.cpms.auth.common.enums.AuthResponseResultEnum;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.common.constant.TokenConstant;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.secure.AuthInfo;
import com.cpms.framework.common.core.secure.TokenInfo;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsJwtUtil;
import com.cpms.framework.common.utils.CsStringUtil;
import com.cpms.framework.redis.utils.CsRedisUtil;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.api.modules.sys.feign.ISysUserClient;
import com.cpms.system.api.modules.sys.vo.SysRoleVO;
import com.cpms.system.api.modules.sys.vo.SysUserLoginVO;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 账号密码授权
 * @author: gulang
 * @time: 2021/6/7 19:43
 */
@Component
public class PasswordAuthen implements IAuthen{
    @Resource
    private ISysUserClient sysUserClient;

    public static final String GRANT_TYPE = "password";
    @Override
    public AuthInfo authentication(UserLoginDTO userLoginDTO) {
        if(!userLoginDTO.getCaptcha().equalsIgnoreCase(
                CsRedisUtil.get(String.format(RedisKeyConstant.UserLogin.CAPTCHA_KEY, userLoginDTO.getCodeKey())))){
            throw new BizException(AuthResponseResultEnum.CAPTCHA_VERIFICATION_EEROR);
        }
        SysUserLginDTO sysUserLginDTO = new SysUserLginDTO();
        sysUserLginDTO.setUserAccount(userLoginDTO.getAccount());
        sysUserLginDTO.setUserPassword(userLoginDTO.getPassword());
        Result<SysUserLoginVO> sysUserLoginBoResult = sysUserClient.sysUserLogin(sysUserLginDTO);
        if(!sysUserLoginBoResult.isSuccess()) {
            throw new BizException(sysUserLoginBoResult);
        }
        SysUserLoginVO sysUserLoginVO = sysUserLoginBoResult.getObj();
        if(StringUtils.isBlank(sysUserLoginVO.getUserAvatar())) {
            sysUserLoginVO.setUserAvatar(TokenConstant.DEFAULT_AVATAR);
        }
        AuthInfo authInfo = new AuthInfo();
        Map<String, Object> claims = buildJwtClaims(sysUserLoginVO);
        TokenInfo tokenInfo = CsJwtUtil.createJwt(claims);
        authInfo.setAccessToken(tokenInfo.getToken());
        authInfo.setExpire(tokenInfo.getExpire());
        authInfo.setUserInfo(sysUserLoginVO);
        return authInfo;
    }

    private Map<String,Object> buildJwtClaims(SysUserLoginVO sysUserLoginVO){
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("userAccount",sysUserLoginVO.getUserAccount());
        claims.put("userName",sysUserLoginVO.getUserName());
        claims.put("userId",sysUserLoginVO.getUserId());
        claims.put("userMobile",sysUserLoginVO.getUserMobile());
        claims.put("deptId",sysUserLoginVO.getDeptId());
        claims.put("tenantId",sysUserLoginVO.getTenantId());
        claims.put("tenantCode",sysUserLoginVO.getTenantCode());
        claims.put("postId",sysUserLoginVO.getPostId());
        claims.put("userSex",sysUserLoginVO.getUserSex());
        claims.put("roleCodes",sysUserLoginVO.getRoles().stream().map(SysRoleVO::getRoleCode).collect(Collectors.toList()));
        claims.put("roleIds",sysUserLoginVO.getRoles().stream().map(SysRoleVO::getRoleId).collect(Collectors.toList()));
        return claims;
    }
}
