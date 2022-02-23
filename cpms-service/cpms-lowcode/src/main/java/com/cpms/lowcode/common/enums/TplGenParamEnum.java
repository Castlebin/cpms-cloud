package com.cpms.lowcode.common.enums;

import com.cpms.lowcode.config.TplCacheManageConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.Map;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/19 16:30
 */
public class TplGenParamEnum {
    /**
     * 生成代码对应的模板类型
     */
    @Getter
    @AllArgsConstructor
    public enum TemplateTypeEnum{
        BOOTNEW("genscaffold", "初始化项目脚手架模板",1, TplCacheManageConfig.genProjectTplFile),
        BOOTDFUNC("genfunc", "生成数据表单功能模板",2, TplCacheManageConfig.genFunTplFile),
        ;
        private String code;

        private String desc;

        private Integer type;

        private Map<String, File> tplFilePathMap;

        public static TemplateTypeEnum getByCode(String code){
            for(TemplateTypeEnum templateTypeEnum : values()){
                if(code.equals(templateTypeEnum.getCode())){
                    return templateTypeEnum;
                }
            }
            return null;
        }

        public static TemplateTypeEnum getByType(Integer type){
            for(TemplateTypeEnum templateTypeEnum : values()){
                if(type.equals(templateTypeEnum.getType())){
                    return templateTypeEnum;
                }
            }
            return null;
        }
    }
}
