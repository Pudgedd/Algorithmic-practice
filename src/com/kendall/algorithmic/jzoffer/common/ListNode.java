package com.kendall.algorithmic.jzoffer.common;

/**
 * @description: 链表节点
 * @author: kendall
 * @since: 2018/9/26
 */
public class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}