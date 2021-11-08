package com.cpms.framework.common.core.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/11/6 19:12
 */
public interface ITreeNode<T> extends Serializable {
    Long getId();

    void setId(Long id);

    Long getParentId();

    void setParentId(Long parentId);

    List<T> getChildren();

    void setChildren(List<T> children);
}
