package day03;

import common.ListNode;

import java.util.Scanner;

/**
 * @description:输入一个链表，输出该链表中倒数第k个结点。
 * @author: kendall
 * @since: 2018/9/26
 */
public class FindKthToTail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        ListNode[] res = new ListNode[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int k = scanner.nextInt();
            int num = scanner.nextInt();
            ListNode root = new ListNode(scanner.nextInt());
            ListNode cur = root;
            for (int j = 1; j < num; j++) {
                cur.setNext(new ListNode(scanner.nextInt()));
                cur = cur.getNext();
            }
            res[i] = fun(root, k);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i].getVal());
        }

    }

    private static ListNode fun(ListNode root,int k) {

        ListNode fast = root;
        ListNode slow = root;

        for (int i = 0; i < k; i++) {
            if (fast.getNext() != null) {
                fast = fast.getNext();
            } else {
                throw new RuntimeException("error");
            }
        }

        while (fast != null) {
            fast = fast.getNext();
            slow = slow.getNext();
        }

        return slow;
    }
}
