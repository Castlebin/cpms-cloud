package com.cpms.lowcode.utils;

import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  字段名称转换工具类
 * @author gulang
 */
@Slf4j
public class CsConvertUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String toJavaType(String jdbcType) {
        if (jdbcType == null || jdbcType.trim().length() == 0) {
            return jdbcType;
        }
        jdbcType = jdbcType.toLowerCase();
        switch (jdbcType) {
            case "nvarchar":
                return "String";
            case "nvarchar2":
                return "String";
            case "char":
                return "String";
            case "varchar":
                return "String";
            case "enum":
                return "String";
            case "set":
                return "String";
            case "text":
                return "String";
            case "nchar":
                return "String";
            case "blob":
                return "byte[]";
            case "integer":
                return "Long";
            case "int":
                return "Integer";
            case "tinyint":
                return "Integer";
            case "smallint":
                return "Integer";
            case "mediumint":
                return "Integer";
            case "bit":
                return "Boolean";
            case "bigint":
                return "Long";
            case "number":
                return "Long";
            case "float":
                return "Float";
            case "double":
                return "Double";
            case "decimal":
                return "BigDecimal";
            case "boolean":
                return "Boolean";
            case "id":
                return "Long";
            case "date":
                return "LocalDateTime";
            case "datetime":
                return "LocalDateTime";
            case "year":
                return "LocalDateTime";
            case "time":
                return "Time";
            case "timestamp":
                return "Timestamp";
            case "numeric":
                return "BigDecimal";
            case "real":
                return "BigDecimal";
            case "money":
                return "Double";
            case "smallmoney":
                return "Double";
            case "image":
                return "byte[]";
            default:
                log.error(">>> jdbcType转javaType失败,未发现的类型:{}",jdbcType);
                break;
        }
        return jdbcType;
    }

    /**
     * 类名称格式化
     * @param s
     * @return
     */
    public static String formatClassName(String s){
        return firstUpperCase(formatName(underLineToHump(s)));
    }

    /**
     * 字段名称格式化
     * @param s
     * @return
     */
    public static String formatFieldName(String s){
        return firstLowerCase(underLineToHump(s));
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String firstLowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String formatName(String s){
        return  s.replaceAll("\\W|[0-9]|(-)", "");
    }

    /** 下划线转驼峰 */
    public static String underLineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
