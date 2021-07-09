package com.cpms.common.exception;
import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: gulang
 * @time: 2021/6/24 9:37
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    public Result<Void> exceptionHandler( Exception e){
        log.error("捕获Exception异常信息:",e);
        return ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_EXCEPTION_ERROR);
    }

    /**
     * 自定义通用业务异常BizException
     */
    @ExceptionHandler(BizException.class)
    public Result<Object> bizExceptionHandler(BizException e) {
        return ResultUtil.error(e.getCode(),e.getMessage(),e.getApplicatonName(),e.getObj());
    }
}
