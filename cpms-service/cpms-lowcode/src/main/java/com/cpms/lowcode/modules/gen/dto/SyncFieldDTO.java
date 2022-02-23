package com.cpms.lowcode.modules.gen.dto;

import com.cpms.framework.mybatis.groups.ValidatorGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/19 18:15
 */
@Data
public class SyncFieldDTO {
    @NotNull(message="tableId不能为空",groups = {ValidatorGroup.Other.class})
    private Long tableId;

    @NotNull(message="dbId不能为空",groups = {ValidatorGroup.Other.class})
    private Long dbId;

    @NotBlank(message="tableName不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="tableName不能为空",groups = {ValidatorGroup.Other.class})
    private String tableName;


    @NotBlank(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    private String dbName;
}
