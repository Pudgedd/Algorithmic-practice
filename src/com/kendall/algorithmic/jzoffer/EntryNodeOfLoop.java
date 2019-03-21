package com.kendall.algorithmic.jzoffer;

import com.kendall.algorithmic.jzoffer.common.ListNode;

/**
 * @description:给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * @author: kendall
 * @since: 2019/3/21
 */
public class EntryNodeOfLoop {

    /**
     * 思路：链表与环的问题可以用快慢两个指针的方法解决。用一个指针每次前进一步，另一个指针每次前进两步。
     * 若没有环，快指针会到达null。有环，则两个指针会在环中相遇，此时让快指针从头开始遍历，速度和慢指针一致，再次相遇时即是入口。
     * 注意空指针。
     *
     * 1、设置快慢指针，假如有环，他们最后一定相遇。
     * 2、两个指针分别从链表头和相遇点继续出发，每次走一步，最后一定相遇与环入口。
     * 证明1：设置快慢指针fast和low，fast每次走两步，low每次走一步。假如有环，两者一定会相遇（因为low一旦进环，可看作fast在后面追赶low的过程，每次两者都接近一步，最后一定能追上）。
     * 证明2：
     * 设：
     * 链表头到环入口长度为--a
     * 环入口到相遇点长度为--b
     * 相遇点到环入口长度为--c
     *
     * 则：相遇时
     * 快指针路程=a+(b+c)k+b ，k>=1  其中b+c为环的长度，k为绕环的圈数（k>=1,即最少一圈，不能是0圈，不然和慢指针走的一样长，矛盾）。
     * 慢指针路程=a+b
     * 快指针走的路程是慢指针的两倍，所以：
     * （a+b）*2=a+(b+c)k+b
     * 化简可得：
     * a=(k-1)(b+c)+c 这个式子的意思是： 链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度。其中k>=1,所以k-1>=0圈。
     * 所以两个指针分别从链表头和相遇点出发，最后一定相遇于环入口，此时从相遇点出发的可能走了一圈或多圈。但是肯定会在入口相遇。
     *
     * @param pHead
     * @return
     */
    private ListNode findEntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null) {
            return null;
        }

        fast = pHead;
        while (slow != pHead) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
