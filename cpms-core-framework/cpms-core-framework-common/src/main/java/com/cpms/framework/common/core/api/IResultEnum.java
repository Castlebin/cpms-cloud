package com.cpms.framework.common.core.api;
import java.io.Serializable;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/24 17:48
 */
public interface IResultEnum extends Serializable {
    Integer getCode();
    String getMessage();
}