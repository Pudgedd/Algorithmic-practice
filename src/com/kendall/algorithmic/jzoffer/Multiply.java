package com.kendall.algorithmic.jzoffer;

/**
 * @description:给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 *
 * @author: kendall
 * @since: 2019/3/11
 */
public class Multiply {
    public static void main(String[] args) {

    }

    /**
     * 不会，看答案
     * 思路：其实B[i]的值用一张图表示，就是一个矩阵，可以看成矩阵中每一行的乘积。b[i]中没有a[i]，其实就是吧a[i]用1代替。
     * 下三角用连乘可以很容求得，上三角，从下向上也是连乘。
     * 因此我们的思路就很清晰了，先算下三角中的连乘，即我们先算出B[i]中的一部分，然后倒过来按上三角中的分布规律，把另一部分也乘进去。
     * b0  【1】a1 a2 a3 a4...an-2 an-1
     * b1   a0【1】a2 a3 a4...an-2 an-1
     * b2   a0 a1【1】a3 a4...an-2 an-1
     * b3   a0 a1 a2【1】a4...an-2 an-1
     * .
     * .
     * bn-2 a0  a1 a2 a3 a4...【1】 an-1
     * bn-1 a0  a1 a2 a3 a4...an-2 【1】
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int[] B = new int[A.length];
        int n = A.length;
        //下半三角形，从b1开始
        B[0] = 1;
        for (int i = 1; i < n; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }

        //上半三角形,b【n-1】不用计算了，因为最后是个1，从n-2开始
        int tmp = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp = tmp * A[i + 1];
            B[i] = B[i] * tmp;
        }
        return B;
    }
}
