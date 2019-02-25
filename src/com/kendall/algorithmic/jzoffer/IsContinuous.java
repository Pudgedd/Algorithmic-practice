package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
 * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
 * 为了方便起见,你可以认为大小王是0。
 * 1
 * 1 3 0 0 5
 * true
 *
 *
 * @author: kendall
 * @since: 2019/2/25
 */
public class IsContinuous {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            int[] arr = new int[5];
            for (int j = 0; j < 5; j++) {
                arr[j] = scanner.nextInt();
            }
            System.out.println(fun(arr));
        }
    }

    /** 2 3 6 0 0
     * 思路：首先明确几个条件
     * 0.顺子是5张连续的牌
     * 1.牌里有4个0，这时true
     * 2.一旦出现重复的，直接false
     * 3.最大的牌减去最小的牌（除大小王）肯定小于5
     * @param numbers
     * @return
     */
    private static boolean fun(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        int[] map = new int[14];
        //设置max和min的时候，一定不要想当然设置为0，会出问题的
        int max = -1;
        int min = 14;
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            map[num]++;
            if (num == 0) {
                continue;
            }
            if (map[num] > 1) {
                return false;
            }
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return (max - min < 5);
    }
}
