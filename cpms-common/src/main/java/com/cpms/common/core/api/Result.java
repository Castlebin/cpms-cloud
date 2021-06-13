package com.cpms.common.core.api;
import lombok.Data;
import java.io.Serializable;



/**
 * @description: 返回结果实体类
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
public class  Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success = false;
    private Integer code;
    private String message;
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
}
