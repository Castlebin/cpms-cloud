package com.cpms.framework.common.core.base;

import java.util.List;

/**
 * 类描述: 公共的分页返回分装
 *
 * @author gulang
 * @date 2021/03/31 16:17
 */
public class BasePageVO<T> {
    /**
     * 总记录数
     */
    private Integer total;
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

    @Override
    public String toString() {
        return "BasePageVO{" +
                "total=" + total +
                ", list=" + list +
                ", otherObj=" + otherObj +
                '}';
    }
}
