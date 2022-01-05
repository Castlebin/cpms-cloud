package com.cpms.gateway.controller;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author gulang
 * @Description: 暴露网关sentinel流控规则信息接口
 * @time: 2021/12/31 15:37
 */
@RestController
@RequestMapping("/gw-flow-rule")
public class RulesController {
    /**
     * API管理
     * @return
     */
    @GetMapping("/api-group")
    public Set<ApiDefinition> apiRules() {
        return GatewayApiDefinitionManager.getApiDefinitions();
    }

    /**
     * 网关流控规则
     * @return
     */
    @GetMapping("/gw-flow")
    public Set<GatewayFlowRule> apiGateway() {
        return GatewayRuleManager.getRules();
    }

}
