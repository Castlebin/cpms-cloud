package com.cpms.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 17:41
 */

public class TenantEntity extends BaseEntity{
    @TableField(fill = FieldFill.INSERT)
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
