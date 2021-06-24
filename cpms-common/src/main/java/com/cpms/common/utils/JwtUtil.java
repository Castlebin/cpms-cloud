package com.cpms.common.utils;

import com.cpms.common.constant.AppConstant;
import com.cpms.common.core.secure.TokenInfo;
import com.google.common.base.Charsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @description: jwt工具类
 * @author: gulang
 * @time: 2021/6/7 10:28
 */
public class JwtUtil {
    /**有效期默认2两小时，单位：毫秒**/
    private static final long TOKEN_EXPIRE = 3600*2*1000;
    private static final String BASE64_SECURITY = Base64.getEncoder().encodeToString(AppConstant.APPLICATION_NAME.getBytes(Charsets.UTF_8));
    /**指定签名的时候使用的签名算法**/
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    /**
     *
     * @param claims 根据业务需要保存在token中的信息,比如用户userId、userName
     * @return
     */
    public static TokenInfo createJwt(Map<String, Object> claims){
        return createJwt(claims, TOKEN_EXPIRE);
    }

    /**
     *
     * @param claims 根据业务需要保存在token中的信息,比如用户userId、userName
     * @param expire 自定义token有效时长，单位：毫秒
     * @return
     */
    public static TokenInfo createJwt(Map<String, Object> claims,long expire){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken")
                .setIssuer("issuser")
                .setAudience("audience")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setClaims(claims)
                .signWith(SIGNATURE_ALGORITHM, signingKey);

        long expMillis = nowMillis + expire;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(builder.compact());
        tokenInfo.setExpire((int)expire / 1000);
        return tokenInfo;
    }

    /**
     * 解析 token信息
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJwt(String jsonWebToken) {
        try {
            return (Claims)Jwts.parser().setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY)).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception var2) {
            return null;
        }
    }

}
