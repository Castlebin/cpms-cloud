package com.cpms.common.core.base;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 17:41
 */
public class TenantEntity extends BaseEntity{
    private String tenantId;

    public TenantEntity() {
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
