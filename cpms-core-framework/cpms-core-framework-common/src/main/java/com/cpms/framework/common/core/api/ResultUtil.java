package com.cpms.framework.common.core.api;

import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.utils.CsDateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 返回结果工具类
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
public class ResultUtil implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 返回成功，不带输出结果
     *
     * @return Result<T>
     */
    public ResultUtil(){

    }
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 根据boolean值返回
     * @param flag
     * @param <T>
     * @return
     */
    public static <T> Result<T> status(boolean flag) {
        return flag ? success(GlobalResponseResultEnum.HANDEL_SUCCESS.getCode(),GlobalResponseResultEnum.HANDEL_SUCCESS.getMessage())
                : error(GlobalResponseResultEnum.HANDEL_FAIL.getCode(),GlobalResponseResultEnum.HANDEL_FAIL.getMessage());
    }

    public static <T> Result<T> success(Integer code,String message) {
        Result<T> result = new Result(false);
        result.setDate(CsDateUtil.dateFormat(new Date()));
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回成功，带输出结果
     *
     * @param obj
     *            输出结果
     * @return Result<T>
     */
    public static <T> Result<T> success(T obj) {
        Result<T> result = new Result(true);
        result.setDate(CsDateUtil.dateFormat(new Date()));
        result.setObj(obj);
        result.setCode(GlobalResponseResultEnum.SUCCESS.getCode());
        result.setMessage(GlobalResponseResultEnum.SUCCESS.getMessage());
        return result;
    }
    
    /**
     * 返回失败，带错误代码和错误消息
     *
     * @param code
     *            错误消息
     * @param message
     *            错误代码
     * @return Result<T>
     */
    public static <T> Result<T> error(Integer code,String message) {
        Result<T> result = new Result(false);
        result.setDate(CsDateUtil.dateFormat(new Date()));
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(Integer code,String message,T obj) {
        Result<T> result = error(code,message);
        result.setObj(obj);
        return result;
    }

    /**
     *  返回下游服务返回的完整信息 A->B
     * @param code
     * @param message
     * @param applicationName
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code,String message,String applicationName,T obj) {
        Result<T> result = error(code,message);
        if(Objects.nonNull(applicationName)) {
            result.setApplicationName(applicationName);
        }
        result.setObj(obj);
        return result;
    }

    /**
     * 功能描述: <返回失败，带错误代码和错误消息>
     */
    public static <T> Result<T> error(IResultEnum resultEnum) {
        Result<T> result = new Result(false);
        result.setDate(CsDateUtil.dateFormat(new Date()));
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }
}

