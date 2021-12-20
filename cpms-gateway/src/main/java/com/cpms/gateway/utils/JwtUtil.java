package com.cpms.gateway.utils;

import com.cpms.gateway.common.enums.GatewayResponseResultEnum;
import com.cpms.gateway.exception.CheckJwtException;
import com.google.common.base.Charsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Objects;

/**
 * @description:
 * @author: gulang
 * @time: 2021/12/20 11:40
 */
@Component
public class JwtUtil {

    private static final String DEFAULT_JWT_SECRETKEY = "cpms";

    public static String getBase64Security(String secretKey){
        String jwtSecretKey = DEFAULT_JWT_SECRETKEY;
        if(!Objects.isNull(secretKey)) {
            jwtSecretKey = secretKey;
        }
        return Base64.getEncoder().encodeToString(jwtSecretKey.getBytes(Charsets.UTF_8));
    }

    /**
     * 解析 token信息
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJwt(String jsonWebToken,String secretKey) {
        try {
            return Jwts.parser().setSigningKey(Base64.getDecoder().decode(getBase64Security(secretKey))).parseClaimsJws(jsonWebToken).getBody();
        }catch (ExpiredJwtException expired){
            throw new CheckJwtException(GatewayResponseResultEnum.TOKEN_EXPIRED_ERROR.getCode(),GatewayResponseResultEnum.TOKEN_EXPIRED_ERROR.getMessage());
        }catch (Exception e){
            throw new CheckJwtException(GatewayResponseResultEnum.TOKEN_EXPIRED_ERROR.getCode(),GatewayResponseResultEnum.TOKEN_EXPIRED_ERROR.getMessage());
        }
    }
}
