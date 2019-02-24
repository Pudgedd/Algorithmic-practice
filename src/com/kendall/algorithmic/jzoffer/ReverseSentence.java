package com.kendall.algorithmic.jzoffer;

/**
 * @description:翻转字符串，例如：“student. a am I”。正确的句子应该是“I am a student.”。
 * 1
 * student. a am I
 * I am a student.
 * @author: kendall
 * @since: 2019/2/24
 */
public class ReverseSentence {
    public static void main(String[] args) {
        String str = "student. a am I";
        System.out.println(fun(str));

    }

    /**
     * 字符串翻转：先将整个字符串翻转，再将单个单词翻转
     *
     * @param str
     * @return
     */
    private static String fun(String str) {
        if (str == null || str.length() == 1) {
            return str;
        }
        char[] chars = str.toCharArray();

        //翻转句子
        reverseWord(chars, 0, chars.length - 1);

        //翻转单词，单词用空格分开
        int pLow = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverseWord(chars, pLow, i - 1);
                pLow = i + 1;
            }
        }

        reverseWord(chars, pLow, chars.length - 1);

        return new String(chars);
    }

    /**
     * 翻转字符串
     *
     * @param chars
     */
    private static void reverseWord(char[] chars, int pLow, int pHigh) {
        char tmp;
        while (pLow < pHigh) {
            tmp = chars[pHigh];
            chars[pHigh] = chars[pLow];
            chars[pLow] = tmp;
            pLow++;
            pHigh--;
        }
    }
}
