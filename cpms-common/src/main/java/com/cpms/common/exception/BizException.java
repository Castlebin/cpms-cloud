package com.cpms.common.exception;

import com.cpms.common.core.api.IResultEnum;
import com.cpms.common.core.api.Result;
import com.cpms.common.enums.GlobalResponseResultEnum;

/**
 * @description: 自定义通用业务异常类
 * @author: gulang
 * @time: 2021/6/23 17:42
 */
public class BizException extends RuntimeException{
    private Integer code;
    private String message;
    private String applicatonName;
    private Object obj;
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getApplicatonName() {
        return applicatonName;
    }

    public void setApplicatonName(String applicatonName) {
        this.applicatonName = applicatonName;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 只有异常信息构造方法
     */
    public BizException(String  message) {
        this.message=message;
    }

    /**
     * 默认异常信息
     */
    public BizException() {
        this(GlobalResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR);
    }

    /**
     *  自定义异常信息
     * @param code
     * @param message
     */
    public BizException(Integer code, String  message) {
        this.code=code;
        this.message=message;
    }

    /**
     *  枚举自定义异常信息
     * @param error
     */
    public BizException(IResultEnum error) {
        this.code=error.getCode();
        this.message=error.getMessage();
    }

    /**
     *  下游服务返回的全部信息
     * @param result
     */
    public BizException(Result result) {
        this.code=result.getCode();
        this.message=result.getMessage();
        this.applicatonName=result.getApplicatonName();
        this.obj=result.getObj();
    }
}
