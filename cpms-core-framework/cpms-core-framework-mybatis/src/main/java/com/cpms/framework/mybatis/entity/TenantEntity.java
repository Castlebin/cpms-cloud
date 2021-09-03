package com.cpms.framework.mybatis.entity;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 17:41
 */

public class TenantEntity extends BaseEntity{
    private Long tenantId;

    public TenantEntity() {
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
