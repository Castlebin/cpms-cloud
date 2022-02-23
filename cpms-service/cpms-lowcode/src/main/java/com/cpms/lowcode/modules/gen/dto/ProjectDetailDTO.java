package com.cpms.lowcode.modules.gen.dto;
import com.cpms.framework.mybatis.groups.ValidatorGroup;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author gulang
 * @Description:
 * @time: 2022/1/22 19:57
 */
@Data
public class ProjectDetailDTO {
    private Long projectId;
    @NotBlank(message="projectName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="projectName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String projectName;
    @NotBlank(message="packageName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="packageName不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String packageName;

    @NotBlank(message="fileEncoding不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="fileEncoding不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String fileEncoding;

    @NotBlank(message="jdkVersion不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="jdkVersion不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String jdkVersion;

    @NotNull(message="projectPort不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private Integer projectPort;

    private String contextPath;

    @NotBlank(message="groupId不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="groupId不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String groupId;

    @NotBlank(message="artifactId不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    @NotNull(message="artifactId不能为空",groups = {ValidatorGroup.Add.class, ValidatorGroup.Update.class})
    private String artifactId;

    private String version;
    private String projectDesc;
    private String teamName;
    private String leader;

    @Valid
    private List<ProjectDbDTO> dbDto;
}
