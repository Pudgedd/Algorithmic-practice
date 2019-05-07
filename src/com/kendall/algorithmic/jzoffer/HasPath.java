package com.kendall.algorithmic.jzoffer;

/**
 * @description:请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 * 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * @author: kendall
 * @since: 2019/5/7
 */
public class HasPath {
    public static void main(String[] args) {
        char[] matrix = "abcesfcsadee".toCharArray();
        System.out.println(hasPath(matrix,3,4,"bcced".toCharArray()));
    }

    /**
     * 典型的回溯算法题，思路见代码，任意找一个起点，然后上下左右进行路径寻找，若失败则找另一个起点，继续寻找。
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null || matrix.length < str.length) {
            return false;
        }

        boolean[] accessed = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, i, j, str, 0, accessed)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 回溯实现
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param curRow   当前遍历到的row
     * @param curCol   当前遍历到的col
     * @param str
     * @param k        当前比较到的str字符
     * @param accessed 记录矩阵中字符是否已经遍历过
     * @return
     */
    private static boolean hasPathCore(char[] matrix, int rows, int cols, int curRow, int curCol, char[] str, int k, boolean[] accessed) {
        int index = curRow * cols + curCol;
        if (curRow < 0 || curRow >= rows || curCol < 0 || curCol >= cols || matrix[index] != str[k] || accessed[index]) {
            return false;
        }
        //当前字符符合，设置为已访问
        accessed[index] = true;
        //遍历结束，找到路径
        if(k == str.length-1){
            return true;
        }

        //没结束，继续往下寻找
        if (hasPathCore(matrix, rows, cols, curRow - 1, curCol, str, k + 1, accessed) ||
                hasPathCore(matrix, rows, cols, curRow, curCol - 1, str, k + 1, accessed) ||
                hasPathCore(matrix, rows, cols, curRow + 1, curCol, str, k + 1, accessed) ||
                hasPathCore(matrix, rows, cols, curRow, curCol + 1, str, k + 1, accessed)) {
            return true;
        }

        //到了这一步，证明前面没有返回true，所以当前路径不可行，必须回退。
        accessed[index] = false;

        return false;
    }
}
