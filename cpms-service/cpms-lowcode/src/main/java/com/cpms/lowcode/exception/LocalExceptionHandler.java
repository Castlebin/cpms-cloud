package com.cpms.lowcode.exception;

import com.cpms.framework.common.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 低代码服务异常捕获
 * @author: gulang
 * @time: 2021/7/7 14:49
 */
@Slf4j
@RestControllerAdvice
public class LocalExceptionHandler extends GlobalExceptionHandler {

}
