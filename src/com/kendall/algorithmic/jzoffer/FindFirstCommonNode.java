package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.ListNode;

/**
 * @description: 输入两个链表，找出它们的第一个公共结点。
 * @author: kendall
 * @since: 2019/1/13
 */
public class FindFirstCommonNode {
    /**
     * 思路：采用从前往后的遍历方式，找到第一个相同的节点。
     * 若两个链表有公共节点，那么从公共节点往后的所有节点都是相同的，也就是说长链表可以跳过开头的数个节点，从与短链表长度一致处开始比较。
     * 如下：
     * 1->2->3->4->5->
     *                  6->7->8
     *          7->8->
     * @param pHead2
     * @return
     */
    private ListNode getFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        if (pHead1 == pHead2) {
            return pHead1;
        }

        int len1 = getListLen(pHead1);
        int len2 = getListLen(pHead2);

        int diffLen = len1 - len2;
        if (diffLen >= 0) {
            pHead1 = walkListLen(pHead1, diffLen);
        } else {
            pHead2 = walkListLen(pHead2, diffLen);
        }

        while (pHead1 != null) {
            if (pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.getNext();
            pHead2 = pHead2.getNext();
        }

        return null;

    }

    private ListNode walkListLen(ListNode node, int len) {
        while (len > 0) {
            len--;
            node = node.getNext();
        }
        return node;
    }

    private int getListLen(ListNode node) {
        int len =0;
        while (node != null) {
            len++;
            node = node.getNext();
        }
        return len;
    }
}
