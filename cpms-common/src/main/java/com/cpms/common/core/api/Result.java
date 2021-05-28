package com.cpms.common.core.api;
import lombok.Data;
import java.io.Serializable;



/**
 * @description: 返回结果实体类
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
@Data
public class  Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success = false;
    private Integer code;
    private String message;
    private String date;
    private T obj;

    public Result(boolean success) {
        this.success = success;
    }
}
