package com.kendall.algorithmic.jzoffer;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @description:给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}，
 * {2,3,4,2,6,[2,5,1]}。
 *
 * @author: kendall
 * @since: 2019/4/8
 */
public class MaxInWindows {
    public static void main(String[] args) {
        int[] num = {2,3,4,2,6,2,5,1};
        ArrayList<Integer> list = maxInWindows(num, 3);
        System.out.println(list);
    }

    /**
     * 思路：窗口其实可以用队列实现。每滑动一次，对于一个新的数字k，将k入队，与队列中的数字进行比较。
     * 若小于k，则将其出队，因为在接下来的窗口滑动中，k是新来的，它们永远不可能成为最大值。
     * 若大于k，则判断其是否还在窗口内，不在窗口中则出队。
     * 队列的第一个元素就是最大值。
     * @param num
     * @param size
     * @return
     */
    private static ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length < size || size < 1) {
            return res;
        }

        //双端队列，用LinkedList也是一样，为了能计算是否过期，所以双端队列存的是数组的下标
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        //构建一个窗口
        for (int i = 0; i < size-1; i++) {
            while (!deque.isEmpty() && num[i] > num[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        //滑动开始
        for (int i = size-1; i < num.length; i++) {
            while (!deque.isEmpty() && num[i] > num[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (i - deque.peekFirst() >= size) {
                deque.pollFirst();
            }
            res.add(num[deque.peekFirst()]);
        }

        return res;
    }
}
