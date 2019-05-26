package me.apqx.algorithm.sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{3, 5, 1, 7, 2};
        System.out.println(Arrays.toString(array));

        System.out.println("----BubbleSort----");
        BubbleSort.bubbleSort(array.clone());
        System.out.println("----SelectionSort----");
        SelectionSort.selectionSort(array.clone());

    }
}
