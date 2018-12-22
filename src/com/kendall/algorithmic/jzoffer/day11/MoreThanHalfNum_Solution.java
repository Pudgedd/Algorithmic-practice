package com.kendall.algorithmic.jzoffer.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * <p>
 * 1
 * 7
 * 1 2 3 2 2 2 5 4 2
 * =================
 * 2
 *
 * @description:
 * @author: kendall
 * @since: 2018/12/10
 */
public class MoreThanHalfNum_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        List[] arrs = new ArrayList[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int arrSize = scanner.nextInt();
            arrs[i] = new ArrayList(arrSize);

            for (int j = 0; j < arrSize; j++) {
                arrs[i].add(scanner.nextInt());
            }
        }

        for (int i = 0; i < groupNum; i++) {
            System.out.println(fun(arrs[i]));
        }
    }

    /**
     * 比较简单，以时间换空间的方法，可以用数组代替map
     *
     * @param arr
     * @return
     */
    private static int fun(List<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> countMap = new HashMap<>(arr.size());

        for (Integer num : arr) {
            if (countMap.containsKey(num)) {
                Integer count = countMap.get(num);
                if (count == (arr.size() / 2)) {
                    return num;
                }

                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        return 0;
    }

    /**
     * map 数组替代hashmap
     * @param array
     * @return
     */
    private static int fun0(int[] array) {
        if(array == null || array.length == 0) {return 0;}
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            if(max < array[i]){
                max = array[i];
            }
        }
        int[] map = new int[max+1];
        for(int i = 0; i < array.length; i++){
            map[array[i]]++;
        }
        for(int i = 0; i < map.length; i++){
            if(map[i] > (array.length/2)){
                return i;
            }
        }
        return 0;
    }

    /**
     * 剑指 offer解法
     * <p>
     * 采用阵地攻守的思想：
     * 第一个数字作为第一个士兵，守阵地；count = 1；
     * 遇到相同元素，count++;
     * 遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
     * 再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
     *
     * @param array
     * @return
     */
    private static int fun2(int[] array) {
        if (array == null || array.length == 0) return 0;
        int res = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res) {
                ++count;
            } else {
                --count;
            }

            if (count == 0) {
                res = array[i];
                count = 1;
            }
        }

        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == res) {
                ++count;
            }
        }
        return count > (array.length / 2) ? res : 0;
    }
}