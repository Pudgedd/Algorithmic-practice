package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.ListNode;

import java.util.Scanner;

/**
 * @description: 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 1
 * 3 3
 * 1 3 7
 * 4 5 6
 * -> 1 3 4 5 6 7
 * @author: kendall
 * @since: 2018/9/28
 */
public class Merge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        ListNode[] res = new ListNode[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            ListNode root1 = new ListNode(scanner.nextInt());
            ListNode cur = root1;
            for (int j = 1; j < num1; j++) {
                cur.setNext(new ListNode(scanner.nextInt()));
                cur = cur.getNext();
            }
            ListNode root2 = new ListNode((scanner.nextInt()));
            cur = root2;
            for (int j = 1; j < num2; j++) {
                cur.setNext(new ListNode(scanner.nextInt()));
                cur = cur.getNext();
            }
            res[i] = fun(root1, root2);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print("{");
            while (res[i] != null) {
                String s = res[i].getNext() == null ? "}" : ",";
                System.out.print(res[i].getVal() + s);
                res[i] = res[i].getNext();
            }
        }

    }

    private static ListNode fun(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode res = list1.getVal() > list2.getVal() ? list2 : list1;
        ListNode pre = new ListNode(res.getVal());
        res = pre;

        while (list1 != null || list2 != null) {
            if (list1 == null) {
                pre.setNext(list2);
                list2 = list2.getNext();
            } else if (list2 == null) {
                pre.setNext(list1);
                list1 = list1.getNext();
            } else if (list1.getVal() <= list2.getVal()) {
                pre.setNext(list1);
                list1 = list1.getNext();
            } else {
                //7 8
                //3 4 9
                pre.setNext(list2);
                list2 = list2.getNext();
            }
            pre = pre.getNext();
        }

        return res.getNext();
    }

}
