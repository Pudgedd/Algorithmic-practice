package com.kendall.algorithmic.jzoffer.common;

/**
 * @description:二叉树，每个节点都有指向父节点的指针
 * @author: kendall
 * @since: 2019/3/23
 */
public class TreeLinkNode {
    int val;
    public TreeLinkNode left = null;
    public TreeLinkNode right = null;
    public TreeLinkNode next = null;

    public TreeLinkNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeLinkNode getLeft() {
        return left;
    }

    public void setLeft(TreeLinkNode left) {
        this.left = left;
    }

    public TreeLinkNode getRight() {
        return right;
    }

    public void setRight(TreeLinkNode right) {
        this.right = right;
    }

    public TreeLinkNode getNext() {
        return next;
    }

    public void setNext(TreeLinkNode next) {
        this.next = next;
    }
}