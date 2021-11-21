package com.cpms.framework.common.core.api;

import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.utils.CsPropsUtil;
import org.slf4j.MDC;

import java.io.Serializable;


/**
 * @description: 返回结果实体类
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success = false;
    private Integer code;
    private String message;
    private String traceId = MDC.get(CoreCommonConstant.TRACE_ID);
    private String date;
    private T obj;
    public Result() {
    }
    public Result(boolean success) {
        this.success = success;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", traceId='" + traceId + '\'' +
                ", date='" + date + '\'' +
                ", obj=" + obj +
                '}';
    }
}
