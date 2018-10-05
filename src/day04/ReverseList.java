package day04;

import common.ListNode;

import java.util.Scanner;

/**
 * @description:输入一个链表，反转链表后，输出新链表的表头。
 * 1
 * 5
 * 1 2 3 4 5-> 5 4 3 2 1
 * @author: kendall
 * @since: 2018/9/27
 */
public class ReverseList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        ListNode[] res = new ListNode[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int num = scanner.nextInt();
            ListNode root = new ListNode(scanner.nextInt());
            ListNode cur = root;
            for (int j = 1; j < num; j++) {
                cur.setNext(new ListNode(scanner.nextInt()));
                cur = cur.getNext();
            }
            res[i] = fun(root);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print("{");
            while (res[i] != null) {
                String s = res[i].getNext() == null ? "}" : ",";
                System.out.print(res[i].getVal()+s);
                res[i] = res[i].getNext();
            }
        }

    }

    private static ListNode fun(ListNode root) {
        if (root == null) {
            return root;
        }

        ListNode next = null;
        ListNode pre = null;

        while (root != null) {
            next = root.getNext();
            root.setNext(pre);
            pre = root;
            root = next;
        }

        return pre;
    }
}
