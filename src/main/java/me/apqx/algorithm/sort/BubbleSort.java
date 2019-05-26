package me.apqx.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    /**
     * 冒泡排序，升序
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 冒泡排序
     *
     * @param up 是否升序
     */
    static void bubbleSort(int[] array, boolean up) {
        if (up) {
            bubbleSort(array);
        } else {
            for (int i = 0; i < array.length - 1; i++) {
                boolean flag = false;
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] < array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        flag = true;
                    }
                }
                if (!flag) break;
            }
            System.out.println(Arrays.toString(array));
        }
    }
}
