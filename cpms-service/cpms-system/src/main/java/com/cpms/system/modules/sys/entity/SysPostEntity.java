package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.framework.mybatis.entity.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_post")
public class SysPostEntity extends TenantEntity {
    /**
     *  指定自增策略
     */
    @TableId(value = "post_id",type = IdType.ASSIGN_ID)
    private Long postId;
    private String postName;
    private String postCode;
    private String postDesc;
}
