package com.kendall.algorithmic.jzoffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)， 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 * 输入一个字符串,包括数字字母符号,可以为空
 * 如果是合法的数值表达则返回该数字，否则返回0
 * <p>
 * 2
 * +2345
 * 1af3
 * ----
 * 2345
 * 0
 * @author: kendall
 * @since: 2019/3/6
 */
public class StrToInt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupSize = scanner.nextInt();
        List<String> strs = new ArrayList<>(groupSize);
        for (int i = 0; i < groupSize; i++) {
            strs.add(scanner.next());
        }

        for (String str : strs) {
            System.out.println(fun(str));
        }
    }

    /**
     * 思路：逐个遍历每个字符，转成数字，若在0-9之间，则乘以进位，与之前数字相加。否则返回0
     * char不能直接强转数字，那样得到是的Ascii.比如int i = '1'，i的值为49。
     * 有一个好方法，可以让char减去'0'，得到的就是数字
     * @param str
     * @return
     */
    private static int fun(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        boolean isNeg = false;
        int index = 0;
        if (chars[0] == '+') {
            isNeg = false;
            index = 1;
        } else if (chars[0] == '-') {
            isNeg = true;
            index = 1;
        }

        int res = 0;
        for (int i = index; i < chars.length; i++) {
            int num = chars[i] - '0';
            if (num < 0 || num > 9) {
                return 0;
            }
            res *= 10;
            res += num;
        }

        return isNeg ? (~res+1) : res;
    }
}
