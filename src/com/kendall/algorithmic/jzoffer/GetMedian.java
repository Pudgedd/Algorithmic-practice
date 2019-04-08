package com.kendall.algorithmic.jzoffer;

import java.util.PriorityQueue;

/**
 * @description:如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 * @author: kendall
 * @since: 2019/4/1
 */
public class GetMedian {
    public static void main(String[] args) {
        int[] ints = {2,4,1,3,5};
        for (int anInt : ints) {
            insert(anInt);
        }
        System.out.println(getMedian());
    }

    /**
     *
     * 为了保证插入新数据和取中位数的时间效率都高效，这里使用大顶堆+小顶堆的容器，并且满足：
     * 1、两个堆中的数据数目差不能超过1，这样可以使中位数只会出现在两个堆的交接处；
     * 2、大顶堆的所有数据都小于小顶堆，这样就满足了排序要求。
     *
     *
     * java的优先队列，默认是小根堆实现，重写comparator的compare方法，就可以实现大根堆
     */
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(((o1, o2) -> o2 - o1));
    private static int count;
    /**
     * 每insert一个num，都要维护原有的数据结构，以便getMedian方法的实现。
     * @param num
     */
    private static void insert(Integer num) {
        if (num == null) {
            return;
        }
        if (count % 2 == 0) {
            minHeap.offer(num);
            Integer min = minHeap.poll();
            maxHeap.offer(min);
        } else {
            maxHeap.offer(num);
            Integer max = maxHeap.poll();
            minHeap.offer(max);
        }
        count++;
    }

    /**
     * @return
     */
    private static Double getMedian() {
        if (count % 2 == 0) {
            return new Double(minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            return new Double(maxHeap.peek());
        }
    }
}