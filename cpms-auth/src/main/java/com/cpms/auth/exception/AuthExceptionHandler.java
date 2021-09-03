package com.cpms.auth.exception;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import com.cpms.common.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @description: 认证服务异常捕获
 * @author: gulang
 * @time: 2021/7/7 14:49
 */
@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler extends GlobalExceptionHandler {

    /**
     * 捕获资源不存在异常NoHandlerFoundException [gateway 网关层不能引入该异常类，不支持servlet，否则报错]
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> noHandlerFoundException(NoHandlerFoundException e) {
        log.error("捕获NoHandlerFoundException异常信息:",e);
        return ResultUtil.error(GlobalResponseResultEnum.NO_HANDLER_FOUND_ERROR.getCode(),e.getMessage());
    }

    /**
     * 请求方式异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.error(GlobalResponseResultEnum.METHOD_NOT_SUPPORTED_ERROR.getCode(),e.getMessage(),null,null);
    }
}
