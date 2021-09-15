package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 14:21
 */
@Data
public class ResetPasswordDTO {
    @NotNull(message="userId不能为空",groups = {UpdateGroup.class})
    private Long userId;

    @NotBlank(message="oldPassword不能为空",groups = {UpdateGroup.class})
    @NotNull(message="oldPassword不能为空",groups = {UpdateGroup.class})
    private String oldPassword;

    @NotBlank(message="newPassword不能为空",groups = {UpdateGroup.class})
    @NotNull(message="newPassword不能为空",groups = {UpdateGroup.class})
    private String newPassword;
}
