package me.apqx.algorithm.selection

import me.apqx.algorithm.sort.bubbleSort
import java.util.*

/**
 * 选择问题：有一组N个数，要确定其中第k个最大值
 */
fun main(args: Array<String>) {
    val array = arrayOf(3, 5, 1, 7, 2)
    val k = 2
    println("${array.toList()} : $k")

    println("----method1----")
    method1(array.clone(), k)
    println("----method2----")
    method2(array.clone(), k)
}

/**
 * 方法1：升序排序，取倒数第k个数
 */
fun method1(array: Array<Int>, k: Int) {
    bubbleSort(array)
    println("=> ${array[array.size - k]}")
}

/**
 * 方法2：取k个数，升序排序，将其余所有数与其比较，填充到对应的位置上，然后取第一个元素
 */
fun method2(array: Array<Int>, k: Int) {
    val subArray = Arrays.copyOfRange(array, 0, k)
    bubbleSort(subArray)
    for (i in k until array.size) {
        if (array[i] > subArray[subArray.size - 1]) {
            push(subArray, subArray.size)
            array[subArray.size - 1] = array[i]
            continue
        }
        for (j in 0 until k) {
            if (array[i] > array[j] && array[i] <= array[j + 1]) {
                push(subArray, j + 1)
                array[j] = array[i]
            }
        }
    }
    println("=> ${subArray[0]}")
}

/**
 * index之前的元素向左平移
 */
fun push(array: Array<Int>, index: Int) {
    for (i in 0 until index) {
        if (i >= array.size - 1) continue
        array[i] = array[i + 1]
    }
}