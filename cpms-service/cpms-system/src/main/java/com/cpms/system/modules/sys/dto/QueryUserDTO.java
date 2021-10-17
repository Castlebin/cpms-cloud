package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 17:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserDTO extends BasePageDTO {
    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户手机号
     */
    private String userMobile;

    /**
     * 用户性别：0-未知，1-男，2-女
     */
    private Integer  userSex;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 岗位ID
     */
    private Long postId;

}
