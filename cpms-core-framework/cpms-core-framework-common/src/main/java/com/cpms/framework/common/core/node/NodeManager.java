package com.cpms.framework.common.core.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gulang
 * @Description: 树形节点管理器
 * @time: 2021/11/5 17:50
 */
public class NodeManager {
    public NodeManager() {
    }

    /**
     *  构建树形节点
     * @param items
     * @param parentId
     * @return
     */
    public static <T extends ITreeNode<T>>  List<T> buildTreeNode(List<T> items, Long parentId) {
        List<T> nodeTree = new ArrayList<>();
        items.stream().peek(e->{
            if (Objects.equals(parentId,e.getParentId())) {
                List<T> childNodes = buildTreeNode(items, e.getId());
                e.setChildren(childNodes);
                nodeTree.add(e);
            }
        }).collect(Collectors.toList());
        return nodeTree;
    }
}
