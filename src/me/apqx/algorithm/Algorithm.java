package me.apqx.algorithm;

import java.util.*;

/**
 * Created by apqx on 2017/4/15.
 * 一些算法问题
 */
public class Algorithm {
    public static void main(String[] args){

    }

    /**
     * Two Sum
     * 给定一个数组，一个目标值，要求找出数组中第一对和为目标值的元素的角标。
     * 思路：建一个Map，从数组头开始遍历，查询Map中target和当前元素的差是否存在，存在的话就找到了，不存在就将当前元素和角标存入Map，继续查询下一个元素。
     * @param array 输入数组
     * @param target 目标值
     * @return 角标
     */
    private static int[] sum(int[] array,int target){
        int[] indexs=new int[2];
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for (int i=0;i<array.length;i++){
            if (map.containsKey(target-array[i])){
                indexs[1]=i;
                indexs[0]=map.get(target-array[i]);
                break;
            }
            map.put(array[i],i);
        }
        return indexs;
    }

    /**
     * Three Sum
     * 给定一个数组，一个目标值，要求找出数组中的三个元素使其和为目标值，不能重复。
     * 思路：先排序，先选定第一个值，计算出剩下两个值之和，这两个值分别从左、从右向中间靠拢。
     * @param array 输入数组
     * @param target 目标值
     * @return List
     */
    public static List<List<Integer>> threeSum(int[] array, int target) {
        Arrays.sort(array);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < array.length-2; i++) {
            if (i == 0 || (i > 0 && array[i] != array[i-1])) {
                //从两头向内靠拢
                int left = i+1, right = array.length-1, sum = target - array[i];
                while (left < right) {
                    if (array[left] + array[right] == sum) {
                        //如果相等，说明找到了一组
                        res.add(Arrays.asList(array[i], array[left], array[right]));
                        //临近的数相同则跳过
                        while (left < right && array[left] == array[left+1])
                            left++;
                        while (left < right && array[right] == array[right-1])
                            right--;
                        left++; right--;
                        //如果偏小，左边的的角标加一，会变大
                    } else if (array[left] + array[right] < sum)
                        left++;
                        //如果偏大，右边的角标减一，会变小
                    else
                        right--;
                }
            }
        }
        return res;
    }

    /**
     * Palindrome Number
     * 判断一个数字是否是回文数字，要考虑负数。
     * 思路：负数不是回文数字，将数字转换为字符数组，判断前后对应的字符是否相同，只要有一个不相同即不是回文数字
     * 或者：直接把这个数字反过来，判断是否相同
     * @param x 给定的数字
     * @return 是否是回文数字
     */
    public static boolean isPalindrome_1(int x) {
        if (x < 0||x%10==0) {
            //负数不是回文数字，尾数是0的不是回文数字
            return false;
        } else {
            //数字转换成字符数组
            char[] nums = String.valueOf(x).toCharArray();
            //比较对应的前后字符是否相同，只要有不相同的，即不是回文数字
            for (int i = 0; i < nums.length / 2; i++) {
                if (nums[i] != nums[nums.length - 1 - i]) {
                    return false;
                }
            }
            return true;
        }
    }
    public static boolean isPalindrome_2(int x){
        if (x<0||x%10==0){
            return false;
        }else {
            int rev=0;
            int temp=x;
            while (temp>0){
                rev=rev*10+temp%10;
                temp=temp/10;
            }
            return temp==rev;
        }
    }

    /**
     * Coin Change
     * 找零钱问题，给定一个数组包含现有的零钱币值，给定一个整数，计算出使用现有的零钱凑够此整数共有几种方法。
     * 思路：定义一个二维数组dp[i][j]，表示使用前i个面值的硬币组成整数j的方案个数，则dp[i][j]可分为两种情况，使用第i个面值的硬币或不使用第
     * i个面值的硬币，不使用的话，有dp[i-1][j]种方案，使用的话，有dp[i][j-coins[i]]种（使用的意思是至少使用一枚，即使用前i种硬币凑成j-coins[i]的方法数）
     * @param amount 给定的整数
     * @param coins 表示面值的数组
     * @return 方案的个数
     */
    public int change(int amount, int[] coins) {
        //使用迭代实现
        //这里使一些值初始化为0，比如dp[0,j]
        //实际情况从1开始
        int[][] dp=new int[coins.length+1][amount+1];
        dp[0][0]=1;
        for(int i=1;i<=coins.length;i++){
            dp[i][0]=1;
            for(int j=1;j<=amount;j++){
                dp[i][j]=dp[i-1][j]+(j>=coins[i-1]?dp[i][j-coins[i-1]]:0);
            }
        }
        return dp[coins.length][amount];
    }
}
