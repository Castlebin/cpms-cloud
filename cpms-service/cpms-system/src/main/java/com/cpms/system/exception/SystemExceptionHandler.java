package com.cpms.system.exception;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @description: 后台服务异常捕获
 * @author: gulang
 * @time: 2021/7/7 14:49
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler extends GlobalExceptionHandler {

}
