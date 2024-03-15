package com.csy.interview.offer_come.design_mode.composite;

import com.alibaba.fastjson.JSON;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class TreeNode {

    private String name;

    private TreeNode parent;

    private Vector<TreeNode> children = new Vector<TreeNode>();

    public TreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void add(TreeNode node) {
        children.add(node);
    }

    public void remove(TreeNode node) {
        children.remove(node);
    }

    public Enumeration<TreeNode> getChildren() {
        return children.elements();
    }

    public static void main(String[] args) {
        TreeNode nodeA = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        nodeA.add(nodeB);
        System.out.println(JSON.toJSONString(nodeA));

        /**
         * 输出结果：
         * {"children":[{"children":[],"name":"B"}],"name":"A"}
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
