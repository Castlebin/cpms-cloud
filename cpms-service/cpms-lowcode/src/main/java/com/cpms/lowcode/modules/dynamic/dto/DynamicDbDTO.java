package com.cpms.lowcode.modules.dynamic.dto;

import com.cpms.framework.mybatis.groups.ValidatorGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/9 16:56
 */
@Data
public class DynamicDbDTO {

    @NotBlank(message="dbType不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbType不能为空",groups = {ValidatorGroup.Other.class})
    private String dbDriverClass;

    @NotBlank(message="dbIp不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbIp不能为空",groups = {ValidatorGroup.Other.class})
    private String dbIp;

    @NotBlank(message="dbPort不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbPort不能为空",groups = {ValidatorGroup.Other.class})
    private String dbPort;

    @NotBlank(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    private String dbName;

    @NotBlank(message="dbUser不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbUser不能为空",groups = {ValidatorGroup.Other.class})
    private String dbUser;

    @NotBlank(message="dbPassword不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbPassword不能为空",groups = {ValidatorGroup.Other.class})
    private String dbPassword;
}
