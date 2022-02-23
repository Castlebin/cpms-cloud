package com.cpms.lowcode.modules.gen.dto;

import com.cpms.framework.mybatis.groups.ValidatorGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author gulang
 * @Description:
 * @time: 2022/1/22 19:57
 */
@Data
public class ProjectDbDTO {
    private Long dbId;
    private Long projectId;
    private String dbDriverClass;
    @NotBlank(message="dbIp不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="dbIp不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String dbIp;
    @NotBlank(message="dbPort不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="dbPort不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String dbPort;

    @NotBlank(message="dbName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="dbName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String dbName;
    @NotBlank(message="dbUser不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="dbUser不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String dbUser;
    @NotBlank(message="dbPassword不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="dbPassword不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String dbPassword;
    private Integer dbPrimary;

}
