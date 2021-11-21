package com.cpms.framework.common.exception;

import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @author gulang
 * @Description: 校验jwt异常类
 * @time: 2021/11/17 19:18
 */
public class CheckJwtException extends RuntimeException implements IException{
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
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public Object getObj() {
        return obj;
    }

    public CheckJwtException() {
    }

    /**
     *  枚举自定义异常信息
     * @param error
     */
    public CheckJwtException(IResultEnum error) {
        this.code=error.getCode();
        this.message=error.getMessage();
    }
}