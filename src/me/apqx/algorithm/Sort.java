package me.apqx.algorithm;

import java.util.Arrays;

/**
 * Created by apqx on 2017/4/15.
 * 一些排序算法。
 */
public class Sort {
    public static void main(String[] args){

    }

    /**
     * 冒泡排序
     * 思想：从左至右迭代，每次比较相邻的两个数，如果前者大于后者，就交换，长度为n的数组需要比较判断n-1次才能完成第一次迭代。结果是最大值一定被排在最后面，
     * 下一次迭代只需比较判断n-2次...如果某次迭代并没有数据交换，及说明已完成排序。
     * 所谓“冒泡”是指每一次迭代，最大值总是会排到最后面。
     * 时间复杂度：n^2
     * 优势：在最好的情况下，只需进行一次迭代即可完成排序。
     * 问题：当数据量非常大时，将消耗大量的时间才能完成排序。
     */
    private static void bubbleSort(int[] array){
        System.out.println(Arrays.toString(array));
        for (int i=0;i<array.length-1;i++){
            int count=0;
            for (int j=0;j<array.length-i-1;j++){
                if (array[j]>array[j+1]){
                    array[j]=array[j]^array[j+1];
                    array[j+1]=array[j]^array[j+1];
                    array[j]=array[j]^array[j+1];
                    count++;
                }
            }
            if (count==0){
                break;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择排序
     * 思想：从左至右迭代，每次迭代要找到最小值，并将它与合适的位置交换，长度为n的数组，第一次迭代需要判断n-1次，第二次迭代判断n-2次...共需进行n-1次迭代。
     * 时间复杂度：n^2
     * 最好的情况下，也要完整的执行所有迭代。
     */
    private static void selectionSort(int[] array){
        System.out.println(Arrays.toString(array));
        for (int i=0;i<array.length-1;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i]>array[j]){
                    array[i]=array[i]^array[j];
                    array[j]=array[i]^array[j];
                    array[i]=array[i]^array[j];
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     * 思想：从第二个数开始，把它和它前面的数比较，如果前面的数大于它，就把前面的数放到他的位置，其实就是将其前面大于它的数向右移动，然后它将插入前面合适的位置。
     */
    private static void insertSort(int[] array){
        System.out.println(Arrays.toString(array));
        int current;
        int frontIndex;
        for (int i=1;i<array.length;i++){
            current=array[i];
            frontIndex=i-1;
            while (frontIndex>=0&&array[frontIndex]>current){
                array[frontIndex+1]=array[frontIndex];
                frontIndex--;
            }
            array[frontIndex+1]=current;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序
     * 思想：在数组中找到一个数，使得左边的元素不大于它，右边的元素都大于它，以此元素为界，将原数组分成左右两个子数组，对子数组递归分组。
     * 时间复杂度：n*logn
     */
    private static void quickSort(int[] array,int start,int end){
        if (start<end){
            int center=partition(array,start,end);
            quickSort(array,start,center-1);
            quickSort(array,center+1,end);
        }
    }
    //找到一个中间值，使得左边的数都小于等于它，右边的数都大于它
    private static int partition(int[] array,int start,int end){
        int last=array[end];
        int center=start-1;
        for (int i=start;i<=end-1;i++){
            //从第一个元素开始，每个元素都和最后一个元素比较，每找到一个元素小于等于最后一个元素，就把这个元素向前移动，最后把最后一个元素移动到对应的位置，这样，
            //其前面的元素都小于等于此元素
            if (array[i]<=last){
                center++;
                int temp=array[center];
                array[center]=array[i];
                array[i]=temp;
            }
        }
        int temp=array[center+1];
        array[center+1]=array[end];
        array[end]=temp;
        return center+1;
    }

    /**
     * 归并排序
     * 思想：分治法，每一个长度大于1的数组都分成左右两部分，在合并时排序，递归执行。
     * 时间复杂度：n*logn
     */
    private static void mergeSort(int[] array,int start,int end){
        if (start<end){
            int center=(start+end)/2;
            mergeSort(array,start,center);
            mergeSort(array,center+1,end);
            merge(array,start,center,end);
        }
    }
    private static void merge(int[] array,int start,int center,int end){
        //左右两个子数组的长度
        int leftCount=center-start+1;
        int rightCount=end-center;
        //创建左右两个子数组，但是元素个数加1
        int[] left=new int[leftCount+1];
        int[] right=new int[rightCount+1];
        //初始化两个子数组
        for (int i=0;i<left.length-1;i++){
            left[i]=array[start+i];
        }
        for (int i=0;i<right.length-1;i++){
            right[i]=array[center+1+i];
        }
        int i=0,j=0;
        //比较两个子数组的元素，把较小的元素填充到原数组里
        for (int k=start;k<=end;k++){
            if (left[i]<=right[j]){
                array[k]=left[i];
                i++;
                //倒数第二个元素，如果左边小于右边，则左边的倒数第一的元素必须大于右边，否则右边的倒数第一的元素有可能被跳过了
                if (i==left.length-1){
                    left[i]=right[j]+1;
                }
            }else {
                array[k]=right[j];
                j++;
                //倒数第二个元素，如果右边小于左边，则右边的倒数第一的元素必须大于左边，否则左边的倒数第一的元素有可能被跳过了
                if (j==right.length-1){
                    right[j]=left[i]+1;
                }
            }
        }
    }

}
