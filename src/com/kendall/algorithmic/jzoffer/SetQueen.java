package com.kendall.algorithmic.jzoffer;

import java.util.Date;

/**
 * @description:八皇后问题，国际象棋棋盘上，任意两个皇后不能处于同一列、同一行、统一斜行。求放置方法。
 * @author: kendall
 * @since: 2019/4/20
 */
public class SetQueen {
    public static void main(String[] args) {
        Date begin =new Date();
        putQueenAtRow(0);
        Date end =new Date();
        System.out.println("解决 " +N+ " 皇后问题，用时：" +String.valueOf(end.getTime()-begin.getTime())+ "毫秒，计算结果："+count);
    }

    /**
     * n皇后
     */
    private static final Integer N = 11;

    /**
     * 放置方法总数
     */
    private static Integer count = 0;

    /**
     * 棋盘，chessBoard[4][3]代表5行4列
     */
    private static short[][] chessBoard = new short[N][N];

    /**
     * 放置n行第皇后，成功返回true
     *
     * @param row 第n行，从0开始
     * @return
     */
    private static void putQueenAtRow(int row) {
        //放置到第N（row从0开始）行，说明已经放置玩最后一行了，返回true
        if (row == N) {
            count++;
            printChessBoard();
            return;
        }

        //遍历当前row，一个格子一个格子尝试放置
        for (int y = 0; y < N; y++) {

            //开始放置前，先还原该行的状态为0
            for (int i = 0; i < N; i++) {
                chessBoard[row][i] = 0;
            }

            if (check(row, y)) {
                chessBoard[row][y] = 1;
                putQueenAtRow(row + 1);
            }

        }

    }

    private static void printChessBoard() {
        System.out.println("第 " + count + " 种解：");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 判断当前位置是否可以放置皇后，其实只用判断该位置上方的其他行中包括左上、中上和右上又没有皇后即可
     *
     * @return
     */
    private static boolean check(int row, int col) {
        //从0行到当前位置的row，一行一行寻找是否有冲突
        for (int i = 0; i <= row; i++) {
            //中上
            if (chessBoard[i][col] == 1) {
                return false;
            }

            //左上，i小于row，只需左col的边界判断。大于0.
            if (col - i >= 0 && chessBoard[row - i][col - i] == 1) {
                return false;
            }

            //右上，i小与row，只需判断col小于N
            if (col + i < N && chessBoard[row - i][col + i] == 1) {
                return false;
            }
        }

        return true;
    }

}
