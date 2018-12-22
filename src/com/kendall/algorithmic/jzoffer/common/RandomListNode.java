package com.kendall.algorithmic.jzoffer.common;

/**
 * @description:
 * @author: kendall
 * @since: 2018/10/23
 */
public class RandomListNode {
    private int label;
    private RandomListNode next = null;
    private RandomListNode random = null;

    public RandomListNode(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public RandomListNode getNext() {
        return next;
    }

    public void setNext(RandomListNode next) {
        this.next = next;
    }

    public RandomListNode getRandom() {
        return random;
    }

    public void setRandom(RandomListNode random) {
        this.random = random;
    }
}
