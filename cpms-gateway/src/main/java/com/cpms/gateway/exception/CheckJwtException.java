package com.cpms.gateway.exception;

/**
 * @author gulang
 * @Description: 校验jwt异常类
 * @time: 2021/11/17 19:18
 */
public class CheckJwtException extends RuntimeException {
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


    public CheckJwtException() {
    }

    /**
     *  枚举自定义异常信息
     */
    public CheckJwtException(Integer code,String message) {
        this.code=code;
        this.message=message;
    }
}
