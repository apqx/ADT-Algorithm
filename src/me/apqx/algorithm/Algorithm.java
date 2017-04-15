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


}
