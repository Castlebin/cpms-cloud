package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
    @NotNull(message="tenantId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
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
}
