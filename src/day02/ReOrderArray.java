package day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * <p>
 * 1
 * 7
 * 2 1 4 3 9 8 0 -> 1 3 9 2 4 8 0
 * @author: kendall
 * @since: 2018/9/25
 */
public class ReOrderArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        ArrayList<Integer>[] argsList = new ArrayList[groupNum];
        for (int i = 0; i < groupNum; i++) {
            argsList[i] = new ArrayList<>();
            int num = scanner.nextInt();
            for (int j = 0; j < num; j++) {
                argsList[i].add(scanner.nextInt());
            }
        }

        for (int i = 0; i < argsList.length; i++) {
            ArrayList<Integer> arrayList = argsList[i];
            Integer[] fun = fun(arrayList);
            System.out.println(Arrays.toString(fun).replaceAll("[,\\[\\]]", ""));
        }
    }

    private static Integer[] fun(ArrayList<Integer> list) {
        if (list == null) throw new RuntimeException("list can not be null");

        Integer[] array = new Integer[list.size()];
        list.toArray(array);

        int index = 0;
        int insertIndex = 0;

        while (index < array.length) {
            if (array[index] % 2 == 1) {
                int tmp = array[index];
                for (int i = index; i > insertIndex; i--) {
                    array[i] = array[i - 1];
                }
                array[insertIndex] = tmp;
                insertIndex++;
            }
            index++;
        }
        return array;
    }
}
