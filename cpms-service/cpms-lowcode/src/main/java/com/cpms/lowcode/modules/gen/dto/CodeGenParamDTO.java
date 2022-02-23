package com.cpms.lowcode.modules.gen.dto;

import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/18 15:35
 */
@Data
public class CodeGenParamDTO {
    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 类名
     */
    private String className;

    private String packageName;

    private String moduleName;
}
