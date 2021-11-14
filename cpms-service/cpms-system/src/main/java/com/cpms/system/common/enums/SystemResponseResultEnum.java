package com.cpms.system.common.enums;


import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum SystemResponseResultEnum implements IResultEnum {
    /**系统服务响应枚举类**/
    ACCOUNT_OR_PASSWORD_CHECK_FAILED_ERROR(50002, "账号或密码不正确！！！"),
    ACCOUNT_FORBIDDEN_ERROR(50003, "账号已禁用！！！"),
    TENANT_FORBIDDEN_ERROR(50004, "账号所属租户已禁用！！！"),
    USER_ALREADY_EXISTS_ERROR(50005, "该账号已存在！！！"),
    ORIGINAL_PASSWORD_NOT_MATCH_ERROR(50006,"原始密码不正确！！！"),
    THERE_ARE_CHILD_NODES_ERROR(50007,"该节点关联子节点不能删除！！！"),
    ACCOUNT_PREFIX_EXISTS_ERROR(50008,"账号前缀已存在！！！"),
    ;

    final Integer code;
    final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    SystemResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
