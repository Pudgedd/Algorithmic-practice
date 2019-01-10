package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。 如果是则输出Yes, 否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 1
 * 6
 * 1 3 2 7 6 4
 * true
 * 1 2 3
 * false
 * @author: kendall
 * @since: 2018/10/22
 */
public class VerifySquenceOfBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        boolean[] res = new boolean[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int size = scanner.nextInt();
            int[] sequence = new int[size];
            for (int j = 0; j < size; j++) {
                sequence[j] = scanner.nextInt();
            }
            res[i] = fun(sequence);
        }
        for (int i = 0; i < groupNum; i++) {
            System.out.println(res[i]);
        }
    }

    /**
     * 1、确定root；
     * 2、遍历序列（除去root结点），找到第一个大于root的位置，则该位置左边为左子树，右边为右子树；
     * 3、遍历右子树（步骤2在找大于root的位置时已经遍历了左子树了），若发现有小于root的值，则直接返回false；
     * 4、分别判断左子树和右子树是否仍是二叉搜索树（即递归步骤1、2、3）。
     *
     * @param sequence
     * @return
     */
    private static boolean fun(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }

        return judge(sequence, 0, sequence.length - 1);
    }

    private static boolean judge(int[] sequence, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return true;
        }

        int root = sequence[endIndex];

        int leftEnd = startIndex;
        while (sequence[leftEnd] < root) {
            leftEnd++;
        }

        for (int i = leftEnd; i < endIndex; i++) {
            if (sequence[i] < sequence[endIndex]) {
                return false;
            }
        }


        return judge(sequence, startIndex, leftEnd - 1) && judge(sequence, leftEnd, endIndex - 1);
    }
}
