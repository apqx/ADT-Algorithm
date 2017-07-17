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
     * 动态规划
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

    /**
     * 动态规划
     * 爬格子问题，给定一个mxn矩阵，每个节点都包含一个数字，从（1,1）开始，每次只能向下或向右走一个格子，要求到达(m,n)，求走过路径中数字和最大的路径，和那个数字
     * 思路：定义一个数组dp[i][j]，表示走到(i,j)时的数字和，则dp[i][j]=max(dp[i-1][j],dp[i][j-1])+array[i][j]，其中，当i=1时,dp[i][j]=dp[i][j-1]+array[i][j],
     * 当j=1时,dp[i][j]=dp[i-1][j]+array[i][j]。
     * @param array 包含每个节点数字的数组
     * @param m 要到达的节点
     * @param n 要到达的节点
     * @return 最大的数字和
     */
    private static int maxNum(int[][] array,int m,int n){
        int[][] dp=new int[m+1][n+1];
        dp[1][1]=array[1][1];
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (i>1&&j>1){
                    //在这里可以记录走过的路径
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+array[i][j];
                }else {
                    if (i==1&&j>1){
                        dp[i][j]=dp[i][j-1]+array[i][j];
                    }
                    if (i>1&&j==1){
                        dp[i][j]=dp[i-1][j]+array[i][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Add two numbers
     * 给出两个非空链表，里面是非负整数，每一个链表的所有数字代表一个整数，不过小位在前，比如234，个位是2，百位是4，把这两个链表表示的数字相加，
     * 结果也是小位在前的链表，求结果
     * 比如：123+284=308
     */
    static class ListNode{
        private int val;
        private ListNode next;
        public ListNode(int digit){
            this.val=digit;
        }
    }

    private static ListNode addTwoNumbers(ListNode n1,ListNode n2){
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        int carry = 0;
        while (n1 != null || n2 != null) {
            int x = (n1 != null) ? n1.val : 0;
            int y = (n2 != null) ? n2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            dummyHead.next = new ListNode(sum % 10);
            dummyHead = dummyHead.next;
            if (n1 != null) n1 = n1.next;
            if (n2 != null) n2 = n2.next;
        }
        if (carry > 0) {
            dummyHead.next = new ListNode(carry);
        }
        return temp.next;
    }
}
