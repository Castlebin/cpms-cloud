package com.cpms.common.core.api;
import com.cpms.common.enums.GlobalResponseResultEnum;
import org.apache.commons.lang3.time.FastDateFormat;
import java.io.Serializable;
import java.util.Date;

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
     * 返回成功，带输出结果
     *
     * @param obj
     *            输出结果
     * @return Result<T>
     */
    public static <T> Result<T> success(T obj) {
        Result<T> result = new Result(true);
        result.setDate(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
        result.setDate(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
     * 功能描述: <返回失败，带错误代码和错误消息>
     */
    public static <T> Result<T> error(GlobalResponseResultEnum resultEnum) {
        Result<T> result = new Result(false);
        result.setDate(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }
}

