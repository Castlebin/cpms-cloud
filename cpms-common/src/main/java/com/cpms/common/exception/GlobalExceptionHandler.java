package com.cpms.common.exception;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: gulang
 * @time: 2021/6/24 9:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    public Result<Void> exceptionHandler( Exception e){
        return ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 自定义异常BizException
     */
    @ExceptionHandler(BizException.class)
    public Result<Void> BizExceptionHandler(BizException e) {
        return ResultUtil.error(e.getCode(),e.getMessage());
    }
}
