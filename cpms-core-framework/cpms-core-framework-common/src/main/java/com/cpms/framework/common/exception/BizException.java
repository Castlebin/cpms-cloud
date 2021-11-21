package com.cpms.framework.common.exception;
import com.cpms.framework.common.core.api.IResultEnum;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;

/**
 * @description: 自定义通用业务异常类
 * @author: gulang
 * @time: 2021/6/23 17:42
 */
public class BizException extends RuntimeException implements IException {
    private Integer code;
    private String message;
    private String traceId;
    private Object obj;

    @Override
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
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

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
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
     *  抛出下游服务返回的完整信息
     * @param result
     */
    public BizException(Result result) {
        this.code=result.getCode();
        this.message=result.getMessage();
        this.traceId=result.getTraceId();
        this.obj=result.getObj();
    }
}
