package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.OtherGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 13:49
 */
@Data
public class SysTenantDTO {
    @NotNull(message="tenantId不能为空",groups = {UpdateGroup.class, DeleteGroup.class,OtherGroup.class})
    private Long tenantId;

    @NotBlank(message="tenantName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="tenantName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String tenantName;

    @NotBlank(message="contacts不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="contacts不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String contacts;

    @NotBlank(message="contactNumber不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="contactNumber不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String contactNumber;

    @NotBlank(message="address不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="address不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String address;

    @NotNull(message="leaseTimeStart不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeStart;

    @NotNull(message="leaseTimeEnd不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeEnd;

    @NotBlank(message="accountPrefix不能为空",groups = {AddGroup.class})
    @NotNull(message="accountPrefix不能为空",groups = {AddGroup.class})
    @Length(min =2,max=2,message="accountPrefix前缀必须为两个字母",groups = {AddGroup.class})
    private String accountPrefix;

    @NotBlank(message="tenantCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="tenantCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String tenantCode;

    private Integer tenantStatus;

    private String tenantDesc;

    /**
     * 租户菜单权限ID
     */
    @NotBlank(message="menuIds不能为空",groups = {OtherGroup.class})
    @NotNull(message="menuIds不能为空",groups = {OtherGroup.class})
    private String menuIds;
}
