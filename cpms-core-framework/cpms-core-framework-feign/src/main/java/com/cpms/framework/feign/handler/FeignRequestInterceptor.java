package com.cpms.framework.feign.handler;

import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.utils.CsWebUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;



/**
 * @description: feign调用之前请求头处理拦截器
 * @author: gulang
 * @time: 2021/9/8 17:12
 */
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        try {
            /**
             * 服务之间通过feign调用接口，请求头信息会丢失，需要在请求之前做一些处理，把需要的信息传递到下游服务
             */
            // feign调用下游服务，传递traceId 和 当前登录用户信息
            template.header(CoreCommonConstant.TRACE_ID, MDC.get(CoreCommonConstant.TRACE_ID));
            template.header(CoreCommonConstant.USER_INFO, MDC.get(CoreCommonConstant.USER_INFO));
            template.header(CoreCommonConstant.CLIENT_IP_ADDR, CsWebUtil.getClientIp());
        } catch (Exception e) {
            log.error("服务之间的调用,处理请求头发生异常:{}", e.getMessage(), e);
        }
    }
}