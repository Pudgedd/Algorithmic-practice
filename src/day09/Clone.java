package day09;

import common.RandomListNode;

/**
 * @description:输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * @author: kendall
 * @since: 2018/10/23
 */
public class Clone {
    public static void main(String[] args) {

    }

    private RandomListNode fun(RandomListNode root) {

        RandomListNode res = null;

        RandomListNode cur = root;
        RandomListNode next = null;
        RandomListNode random = null;
        RandomListNode copy = null;

//     复制链表
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(new RandomListNode(cur.getLabel()));
            cur.getNext().setNext(next);
            cur = next;
        }

//      复制随机节点
        cur = root;
        while (cur != null) {
            copy = cur.getNext();
            random = cur.getRandom();
            copy.setRandom(random == null ? null : random.getNext());
            cur = copy.getNext();
        }

//      拆分
        cur = root;
        res = root.getNext();
        while (cur != null) {
            copy = root.getNext();
            next = copy.getNext();
            cur.setNext(next);
            copy.setNext(next == null ? null : next.getNext());
            cur = next;
        }

        return res;
    }
}
