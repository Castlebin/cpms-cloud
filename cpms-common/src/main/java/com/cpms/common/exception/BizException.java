package com.cpms.common.exception;

import com.cpms.common.core.api.IResultCode;
import com.cpms.common.enums.GlobalResponseResultEnum;

/**
 * @description: 自定义业务统一异常类
 * @author: gulang
 * @time: 2021/6/23 17:42
 */
public class BizException extends RuntimeException{
    private Integer code;
    private String message;

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
        this(GlobalResponseResultEnum.INTERNAL_SERVER_ERROR);
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
    public BizException(IResultCode error) {
        this.code=error.getCode();
        this.message=error.getMessage();
    }
}
