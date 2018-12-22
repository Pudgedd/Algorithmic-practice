package com.kendall.algorithmic.jzoffer.day07;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description:输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * 2
 * 5
 * 1 2 3 4 5
 * 4 3 5 1 2
 * false
 * <p>
 * 5
 * 1 2 3 4 5
 * 4 5 3 2 1
 * true
 * @author: kendall
 * @since: 2018/10/21
 */
public class IsPopOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        boolean[] res = new boolean[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int size = scanner.nextInt();
            int[] pushArr = new int[size];
            int[] popArr = new int[size];
            for (int j = 0; j < size; j++) {
                pushArr[j] = scanner.nextInt();
            }
            for (int j = 0; j < size; j++) {
                popArr[j] = scanner.nextInt();
            }
            res[i] = fun(pushArr, popArr);
        }
        for (int i = 0; i < groupNum; i++) {
            System.out.println(res[i]);
        }
    }

    private static boolean fun(int[] pushArr, int[] popArr) {
        if (pushArr == null || popArr == null || pushArr.length == 0 || popArr.length == 0 || pushArr.length != popArr.length)
            return false;

        Stack<Integer> pushStack = new Stack<>();
        int indexOfPopArr = 0;
        for (int i = 0; i < pushArr.length; i++) {
            pushStack.push(pushArr[i]);
            while (pushStack.size() > 0 && pushStack.peek() == popArr[indexOfPopArr]) {
                indexOfPopArr++;
                pushStack.pop();
            }
        }


        return pushStack.empty();
    }
}
