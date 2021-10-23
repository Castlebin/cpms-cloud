package com.cpms.framework.common.exception;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> exceptionHandler(Exception e){
        log.error("捕获Exception异常信息:",e);
        return ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_EXCEPTION_ERROR);
    }

    /**
     * 自定义通用业务异常BizException
     */
    @ExceptionHandler(BizException.class)
    public Result<Object> bizExceptionHandler(BizException e) {
        return ResultUtil.error(e.getCode(),e.getMessage(),e.getApplicationName(),e.getObj());
    }

    /**
     * 方法参数校验不通过异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> validatedHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ResultUtil.error(GlobalResponseResultEnum.PARAM_VALID_ERROR.getCode(),fieldError.getDefaultMessage());
            }
        }
        return ResultUtil.error(GlobalResponseResultEnum.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error("捕获Exception异常信息:",e);
        return ResultUtil.error(GlobalResponseResultEnum.PARAMETER_BOYDY_EMPTY_ERROR);
    }

}
