package com.cpms.common.core.base;

import lombok.ToString;

import java.io.Serializable;

/**
 * 类描述: 分页基类
 *
 * @author gulang
 * @date 2021/03/31 16:08
 */
@ToString
public class BasePage implements Serializable {
    /**
     * 默认分页大小
     */
    public static  final Integer DEFAULT_SIZE = 10;

    /**
     * 默认第几页
     */
    public static final Integer DEFAULT_NO = 1;

    /**
     * 第几页
     */
    private Integer pageNo;

    /**
     * 分页大小
     */
    private Integer pageSize;

    public Integer getPageNo(){
        if(pageNo == null || pageNo <=0) {
            pageNo = DEFAULT_NO;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }

    public Integer getPageSize(){
        if(pageSize == null || pageSize <=0) {
            pageSize = DEFAULT_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public BasePage(){
        this.pageNo = DEFAULT_NO;
        this.pageSize = DEFAULT_SIZE;
    }

    public BasePage(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
