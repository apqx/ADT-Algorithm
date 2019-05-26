package me.apqx.algorithm.recursion

fun main(args: Array<String>) {
    val str = "12345"
    println("num = $str")
    println("----printRecursion----")
    printStrRecursion(str)
    println("----printIteration----")
    printStrIteration(str)
}

/**
 * 递归的打印字符串的每一个字符
 */
fun printStrRecursion(str: String) {
    if (str.length == 1) {
        println(str)
    } else{
        println(str.toCharArray()[0])
        printStrRecursion(str.substring(1))
    }
}

/**
 * 迭代的打印字符串的每一个字符
 */
fun printStrIteration(str: String) {
    for (i in 0 until str.length) {
        println(str.toCharArray()[i])
    }
}