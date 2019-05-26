package me.apqx.algorithm.selection;

import me.apqx.algorithm.sort.BubbleSort;

import java.util.Arrays;

public class Select {
    public static void main(String[] args) {
        int[] array = new int[]{3, 5, 1, 7, 2};
        int k = 2;
        System.out.println(Arrays.toString(array) + " : " + k);

        System.out.println("----method1----");
        method1(array.clone(), k);
        System.out.println("----method2----");
        method2(array.clone(), k);
    }

    /**
     * 方法1：升序排序，取倒数第k个数
     */
    static void method1(int[] array, int k) {
        BubbleSort.bubbleSort(array);
        System.out.println("=>" + array[array.length - k]);
    }

    /**
     * 方法2：取k个数，升序排序，将其余所有数与其比较，填充到对应的位置上，然后取第一个元素
     */
    static void method2(int[] array, int k) {
        int[] subArray = Arrays.copyOfRange(array, 0, k);
        BubbleSort.bubbleSort(subArray);
        for (int i = 0; i < array.length; i++) {
            if (array[i] > subArray[subArray.length - 1]) {
                push(subArray, subArray.length);
                array[subArray.length - 1] = array[i];
                continue;
            }
            for (int j = 0; j < k; j++) {
                if (array[i] > array[j] && array[i] <= array[j + 1]) {
                    push(subArray, j + 1);
                    array[j] = array[i];
                }
            }
        }
        System.out.println("=>" + subArray[0]);
    }

    /**
     * index之前的元素向左平移
     */
    static void push(int[] array, int index) {
        for (int i = 0; i < index; i++) {
            if (i >= array.length - 1) continue;
            array[i] = array[i + 1];
        }
    }
}
