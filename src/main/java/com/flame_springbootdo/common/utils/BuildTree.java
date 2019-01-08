package com.flame_springbootdo.common.utils;

import com.flame_springbootdo.common.domain.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/9/30 12:07
 * @Version 1.0
 */
public class BuildTree {

    public static <T> Tree<T> build(List<Tree<T>> nodes) {

        //如果节点的List为null，就返回null
        if (nodes==null) {
            return null;
        }

        //没有父节点节点列表
        List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

        for (Tree<T> children : nodes) {
            //遍历所有的节点，找出所有的顶级节点，将没有父节点的节点存起来
            String pid = children.getParentId();
            if (pid==null || "0".equals(pid)) {
                topNodes.add(children);
                //只要没有父节点，添加后就跳过
                continue;

            }
            //那么剩下的节点都是有父节点,我们给这个孩子节点找父亲节点
            for (Tree<T> parent : nodes) {
                String id = parent.getId();
                if (id!=null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setHasChildren(true);
                    //如果找到父节点并将子节点设置到这个父节点上就可以停止继续循环查找
                    continue;
                }

            }

        }

        //把根节点返回，如果没有父节点不只一个，我们给这些节点创建一个根节点
        Tree<T> root = new Tree<T>();
        if (topNodes.size()==1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setHasParent(false);
            root.setHasChildren(true);
            root.setChecked(true);
            root.setChildren(topNodes);
            root.setText("顶级节点");
            Map<String, Object> state = new HashMap<>(16);
            root.setState(state);
        }
        System.out.println("root:"+root);
        return root;
    }

    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
        if (nodes==null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

        for (Tree<T> children : nodes) {
            String pid = children.getParentId();
            if (pid==null || idParam.equals(pid)) {
                topNodes.add(children);
                continue;

            }

            for (Tree<T> parent : nodes) {
                String id = parent.getId();
                if (id!=null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setHasChildren(true);
                    continue;
                }
            }
        }
        return topNodes;
    }


}
