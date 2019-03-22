package com.kendall.algorithmic;

import com.kendall.algorithmic.jzoffer.common.ListNode;

/**
 * @description:在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * @author: kendall
 * @since: 2019/3/21
 */
public class DeleteDuplication {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node3_1 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node4_1 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.setNext(node2).setNext(node3).setNext(node3_1).setNext(node4).setNext(node4_1).setNext(node5);
        ListNode node = deleteDuplication(head);
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
    }

    /**
     * 思路：遍历，删除
     *
     * @param pHead
     * @return
     */
    private static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        int val = pHead.val+1;
        ListNode myHead = new ListNode(val);
        myHead.next = pHead;

        ListNode pre = myHead;
        ListNode next = null;
        while (pHead != null && pHead.next!= null) {
            next = pHead.next;
            if (pHead.val == next.val) {
                while (pHead != null && pHead.val == next.val) {
                    pHead = pHead.next;
                }
                pre.next = pHead;
            } else {
                pre = pHead;
                pHead = next;
            }
//            next = pHead.next; 可能会空指针，比如1-1-1-1-1-1-1，pHead为null，换个方式，将next提到前面更新指针
        }

        return myHead.next;
    }
}
