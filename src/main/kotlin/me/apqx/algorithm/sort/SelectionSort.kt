package me.apqx.algorithm.sort

/**
 * 选择排序
 */
fun selectionSort(array: Array<Int>) {
    for (i in 0 until array.size - 1) {
        for (j in i until array.size) {
            if (array[i] > array[j]) {
                var temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
        }
    }
    println(array.toList())
}