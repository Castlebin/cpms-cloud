package com.cpms.framework.common.exception;

import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @author gulang
 * @Description: 无权限访问异常
 * @time: 2021/11/18 20:09
 */
public class ForbiddenException extends RuntimeException implements IException{
    private Integer code;
    private String message;
    private String traceId;
    private Object obj;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Object getObj() {
        return obj;
    }

    public ForbiddenException() {
    }

    /**
     *  枚举自定义异常信息
     * @param error
     */
    public ForbiddenException(IResultEnum error) {
        this.code=error.getCode();
        this.message=error.getMessage();
    }
}
