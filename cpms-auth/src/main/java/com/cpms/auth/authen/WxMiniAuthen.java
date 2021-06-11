package com.cpms.auth.authen;

import com.cpms.auth.common.enums.UserLoginEnum;
import com.cpms.auth.dto.UserLoginDTO;
import com.cpms.common.core.secure.AuthInfo;
import org.springframework.stereotype.Component;

/**
 * @description: 微信小程序、公众号登录认证
 * @author: gulang
 * @time: 2021/6/8 10:25
 */
@Component
public class WxMiniAuthen implements IAuthen{
    public static final  String AUTHEN_TYPE = UserLoginEnum.WX_MINI.getName();
    public static final String AVATAR  ="https://file.service.qq.com/user-files/uploads/201708/725215badc6442de7c5dc668ebc9b5b1.png";
    @Override
    public AuthInfo authentication(UserLoginDTO userLoginDTO) {
        System.out.println("微信小程序、公众号登录认证");
        return null;
    }
}
