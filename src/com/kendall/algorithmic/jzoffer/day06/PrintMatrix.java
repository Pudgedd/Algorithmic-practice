package com.kendall.algorithmic.jzoffer.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字 1
 * 4 4
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * @author: kendall
 * @since: 2018/10/5
 */
public class PrintMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        List<int[][]> arr = new ArrayList();

        for (int i = 0; i < groupNum; i++) {
            int[][] matrix = new int[scanner.nextInt()][scanner.nextInt()];
            arr.add(matrix);
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[row][col] = scanner.nextInt();
                }
            }
        }

        for (int i = 0; i < groupNum; i++) {
            int[][] matrix = arr.get(i);
            System.out.println(fun(matrix));
        }
    }

    private static String fun(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int row = matrix.length;
        if (row == 0) {
            return null;
        }
        int col = matrix[0].length;
        List<Integer> res = new ArrayList<>();

        int left = 0, right = col - 1, top = 0, buttom = row - 1;

        while (left <= right && top <= buttom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int j = top + 1; j <= buttom; j++) {
                res.add(matrix[j][right]);
            }
            if (top != buttom) {
                for (int t = right - 1; t >= left; t--) {
                    res.add(matrix[buttom][t]);
                }
            }
            if (left != right) {
                for (int l = buttom - 1; l > top; l--) {
                    res.add(matrix[l][left]);
                }
            }
            left++;
            right--;
            top++;
            buttom--;
        }

        return serialize(res);
    }

    private static String serialize(List<Integer> res) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Integer re : res) {
            sb.append(re);
            count++;
            if (count == res.size()) {
                sb.append(".");
            } else {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
