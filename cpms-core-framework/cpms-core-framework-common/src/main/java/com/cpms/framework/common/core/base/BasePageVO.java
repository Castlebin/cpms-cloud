package com.cpms.framework.common.core.base;

import java.util.List;

/**
 * 类描述: 公共的分页返回分装
 *
 * @author gulang
 * @date 2021/03/31 16:17
 */
public class BasePageVO<T> extends BasePageDTO {
    /**
     * 总记录数
     */
    private Integer total = 0;
    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 附带信息
     */
    private Object otherObj;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Object getOtherObj() {
        return otherObj;
    }

    public void setOtherObj(Object otherObj) {
        this.otherObj = otherObj;
    }

    public BasePageVO() {
        super();
    }

    public BasePageVO(Integer pageNo, Integer pageSize, Integer total, List<T> list) {
        super(pageNo, pageSize);
        this.list = list;
        this.total = total;
    }
}