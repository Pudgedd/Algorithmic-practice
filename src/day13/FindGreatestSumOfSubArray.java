package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
 * 今天测试组开完会后,他又发话了:
 * 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 * <p>
 * 1
 * 8
 * 6 -3 -2 7 -15 1 2 2
 * 8
 *
 * @description:
 * @author: kendall
 * @since: 2018/12/19
 */
public class FindGreatestSumOfSubArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        List[] arrs = new ArrayList[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int arrSize = scanner.nextInt();
            arrs[i] = new ArrayList<Integer>(arrSize);

            for (int j = 0; j < arrSize; j++) {
                arrs[i].add(scanner.nextInt());
            }
        }

        for (int i = 0; i < groupNum; i++) {
            System.out.println(fun(arrs[i]));
        }
    }

    /**
     * 基础的动态规划解决
     * f(i):以arrs[i]结尾的连续子序列的最大和
     * f(i) = Math.max(f(i-1)+arr[i],arr[i])
     * 为什么不是：f(i) = Math.max(f(i-1)+arr[i],f(i-1))？ 一开始搞错了，f(i)代表的是arrs[i]结尾，必须包含当前数字
     * 即：加入你f(i-1)加上当前数字还要小与当前数字的话，证明f(i-1)小于0，而加上一个负数只会减少最大和
     * <p>
     * 用一个res变量记录最大的f(i)，res即为结果
     *
     * @return
     */
    private static int fun1(int[] arrs) {
        int res = arrs[0];
        int max = arrs[0];
        for (int i = 1; i < arrs.length; i++) {
            max = Math.max(max + arrs[i], arrs[i]);
            res = Math.max(max, res);
        }
        return res;
    }

    /**
     * 思路：最大连续子序列和
     *
     * @param list
     * @return
     */
    private static int fun(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }

        //注意细节！不能初始化为0
        Integer[] arrs = list.stream().toArray(Integer[]::new);
        int maxSum = arrs[0];
        int sum = arrs[0];

        for (int i = 1; i < arrs.length; i++) {
            sum += arrs[i];
            if (maxSum < sum) {
                maxSum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }
}
