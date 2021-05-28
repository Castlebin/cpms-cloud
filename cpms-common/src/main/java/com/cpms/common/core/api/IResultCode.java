package com.cpms.common.core.api;
import java.io.Serializable;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
public interface IResultCode extends Serializable {
    Integer getCode();
    String getMessage();
}