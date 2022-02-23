package com.cpms.lowcode.modules.gen.dto;

import com.cpms.framework.mybatis.groups.ValidatorGroup;
import com.cpms.lowcode.modules.dynamic.dto.DbTableDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2022/1/26 20:05
 */
@Data
public class GenFuncDTO {
    @NotNull(message="projectId不能为空",groups = {ValidatorGroup.Other.class})
    private Long projectId;

    @NotNull(message="dbId不能为空",groups = {ValidatorGroup.Other.class})
    private Long dbId;

    @NotBlank(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="dbName不能为空",groups = {ValidatorGroup.Other.class})
    private String dbName;

    @NotEmpty(message="tableList不能为空",groups = {ValidatorGroup.Other.class})
    @NotNull(message="tableList不能为空",groups = {ValidatorGroup.Other.class})
    private List<DbTableDTO> tableList;
}
