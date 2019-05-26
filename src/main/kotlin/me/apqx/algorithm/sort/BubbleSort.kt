package me.apqx.algorithm.sort

/**
 * 冒泡排序，升序
 */
fun bubbleSort(array: Array<Int>) {
    for (i in 0 until array.size - 1) {
        var flag = false
        for (j in 0 until array.size - i - 1) {
            if (array[j] > array[j + 1]) {
                var temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
                flag = true
            }
        }
        if (!flag) break
    }
    println(array.toList())
}

/**
 * 冒泡排序
 *
 * @param up 是否升序
 */
fun bubbleSort(array: Array<Int>, up: Boolean) {
    if (up) {
        bubbleSort(array)
    } else {
        for (i in 0 until array.size - 1) {
            var flag = false
            for (j in 0 until array.size - i - 1) {
                if (array[j] < array[j + 1]) {
                    var temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                    flag = true
                }
            }
            if (!flag) break
        }
    }
    println(array.toList())
}