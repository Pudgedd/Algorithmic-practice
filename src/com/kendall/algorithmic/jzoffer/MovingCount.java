package com.kendall.algorithmic.jzoffer;

/**
 * @description: 地上有一个m行和n列的方格。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * @author: kendall
 * @since: 2019/5/8
 */
public class MovingCount {
    public static void main(String[] args) {
    }

    /**
     * 思路：回溯法，思路比较简单。
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }

        boolean[][] accessed = new boolean[rows][cols];
        return movingCore(threshold, rows, cols, 0, 0, accessed);
    }

    private int movingCore(int threshold, int rows, int cols, int rowIndex, int colIndex, boolean[][] accessed) {
        if (rowIndex < 0 || rowIndex >= rows || colIndex < 0 || colIndex >= cols || accessed[rowIndex][colIndex] || canNotMove(threshold, rowIndex, colIndex)) {
            return 0;
        }

        accessed[rowIndex][colIndex] = true;

        return 1 + movingCore(threshold, rows, cols, rowIndex + 1, colIndex, accessed)
                + movingCore(threshold, rows, cols, rowIndex, colIndex + 1, accessed)
                + movingCore(threshold, rows, cols, rowIndex - 1, colIndex, accessed)
                + movingCore(threshold, rows, cols, rowIndex, colIndex - 1, accessed);
    }


    private boolean canNotMove(int threshold, int rowIndex, int colIndex) {
        return sumNum(rowIndex) + sumNum(colIndex) > threshold;
    }

    private int sumNum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }

        return res;
    }
}
