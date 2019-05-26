package me.apqx.algorithm.recursion;

public class Recursion {
    public static void main(String[] args) {
        String str = "12345";
        System.out.println("num = " + str);
        System.out.println("----printRecursion----");
        printStrRecursion(str);
        System.out.println("----printIteration----");
        printStrIteration(str);
    }

    /**
     * 递归的打印字符串的每一个字符
     */
    static void printStrRecursion(String str) {
        if (str.length() == 1) {
            System.out.println(str);
        } else {
            System.out.println(str.charAt(0));
            printStrRecursion(str.substring(1));
        }
    }

    /**
     * 迭代的打印字符串的每一个字符
     */
    static void printStrIteration(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
    }
}
