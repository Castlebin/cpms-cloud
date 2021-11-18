package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author gulang
 * @Description:
 * @time: 2021/11/16 20:13
 */
@Data
public class PersonalInfoDTO {
    /**
     * 用户昵称
     */
    @NotBlank(message="userName不能为空",groups = {UpdateGroup.class})
    @NotNull(message="userName不能为空",groups = {UpdateGroup.class})
    private String userName;

    /**
     * 真实姓名
     */
    @NotBlank(message="userRealName不能为空",groups = {UpdateGroup.class})
    @NotNull(message="userRealName不能为空",groups = {UpdateGroup.class})
    private String userRealName;

    /**
     * 用户生日
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @JsonFormat( pattern = "yyyy-MM-dd" ,timezone="GMT+8")
    @NotNull(message="userBirthday不能为空",groups = {UpdateGroup.class})
    private LocalDate userBirthday;

    /**
     * 用户手机号
     */
    @NotBlank(message="userMobile不能为空",groups = {UpdateGroup.class})
    @NotNull(message="userMobile不能为空",groups = {UpdateGroup.class})
    private String userMobile;
    /**
     * 用户性别：0-未知，1-男，2-女
     */
    @NotNull(message="userSex不能为空",groups = {UpdateGroup.class})
    private Integer  userSex;
}
