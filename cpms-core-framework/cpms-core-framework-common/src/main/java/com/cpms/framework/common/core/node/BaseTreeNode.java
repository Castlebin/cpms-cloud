package com.cpms.framework.common.core.node;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gulang
 * @Description:
 * @time: 2021/11/5 17:14
 */
public class BaseTreeNode<T> implements ITreeNode<T> {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private List<T> children;
    private Boolean hasChildren;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public List<T> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    @Override
    public void setChildren(List<T> children) {
        this.children = children;
    }

    public void setHasChildren(final Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
    @Override
    public Boolean getHasChildren() {
        if (this.children == null) {
            return  false;
        }
        return this.children.size() > 0 ? true : (!Objects.isNull(this.hasChildren) ? this.hasChildren:false);
    }
}
