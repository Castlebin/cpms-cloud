package com.cpms.framework.common.core.base;

import java.io.Serializable;

/**
 * 类描述: 分页基类
 * @author gulang
 * @date 2021/03/31 16:08
 */
public class BasePageDTO  implements Serializable {
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
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     *  limit 分页索引
     */
    private Integer startIndex;


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

    public BasePageDTO(){
        this.pageNo = DEFAULT_NO;
        this.pageSize = DEFAULT_SIZE;
    }

    public BasePageDTO(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        startIndex = (getPageNo() - 1)*getPageSize();
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
