package com.cpms.common.exception;

/**
 * @description: 业务异常接口
 * @author: gulang
 * @time: 2021/7/9 17:26
 */
public interface IBizException {
    /**
     * 返回状态码
     * @return
     */
     Integer getCode();

    /**
     * 返回异常信息
     * @return
     */
     String getMessage();

    /**
     * 所属服务名称
     * @return
     */
     String getApplicationName();

    /**
     *  返回数据
     * @return
     */
     Object getObj();
}
