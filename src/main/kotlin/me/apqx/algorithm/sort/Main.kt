package me.apqx.algorithm.sort

fun main(args: Array<String>) {
    val array = arrayOf(3, 5, 1, 7, 2)
    println(array.toList())

    println("----BubbleSort----")
    bubbleSort(array.clone())

    println("----SelectionSort----")
    selectionSort(array.clone())

}