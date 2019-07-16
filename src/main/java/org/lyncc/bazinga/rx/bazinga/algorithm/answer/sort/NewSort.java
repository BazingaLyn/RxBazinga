package org.lyncc.bazinga.rx.bazinga.algorithm.answer.sort;

import com.alibaba.fastjson.JSON;


public class NewSort {






    public static void main(String[] args) {

        int[] nums = new int[]{5,4,8,1,3,9,7};
        mergeSort(nums);
        System.out.println(JSON.toJSONString(nums));

    }


    public static void mergeSort(int[] nums){
        _mergeSort(nums,0,nums.length-1);
    }

    private static void _mergeSort(int[] nums,int from ,int end){

        if(from >= end){
            return;
        }

        int mid = (from + end)/2;
        _mergeSort(nums,from,mid);
        _mergeSort(nums,mid+1,end);
        _merge(nums,from,mid,end);
    }

    private static void _merge(int[] nums, int from, int mid, int end) {

        int[] tmp = new int[end-from+1];
        for (int i = from; i <= end ; i++) {
            tmp[i-from] = nums[i];
        }

        int leftIndex = from;
        int rightIndex = mid +1;

        for (int i = from; i <= end; i++) {

            if(leftIndex > mid){
                nums[i] = tmp[rightIndex-from];
                rightIndex++;
            }else if(rightIndex > end){
                nums[i] = tmp[leftIndex-from];
                leftIndex ++;
            }else if(tmp[leftIndex - from] < tmp[rightIndex -from]){
                nums[i] = tmp[leftIndex - from];
                leftIndex ++;
            }else{
                nums[i] = tmp[rightIndex - from];
                rightIndex ++;
            }

        }

    }




    public static void insertSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(nums[j] < nums[j-1]){
                    int tmp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tmp;
                }else{
                    break;
                }


            }
        }
    }


    /**
     * @param nums
     */
    public static void baseQuickSort(int[] nums){
        _baseQuickSort2(nums,0,nums.length-1);

    }


    private static void _baseQuickSort2(int[] nums, int from, int end) {
        if(from >= end) return;

        int partitionIndex = partition2(nums, from, end);

        _baseQuickSort(nums,from,partitionIndex-1);
        _baseQuickSort(nums,partitionIndex+1,end);
    }


    private static void _baseQuickSort(int[] nums, int from, int end) {

        if(from >= end) return;

        int partitionIndex = partition(nums, from, end);

        _baseQuickSort(nums,from,partitionIndex-1);
        _baseQuickSort(nums,partitionIndex+1,end);


    }

    private static int partition(int[] nums, int from, int end) {
        int currentStandard = nums[from];

        int partitionIndex = from;

        for (int i = from + 1; i <= end; i++) {
            if(nums[i] < currentStandard){
                int temp = nums[partitionIndex+1];
                nums[partitionIndex+1] = nums[i];
                nums[i] = temp;

                partitionIndex++;
            }
        }

        int tmp = nums[from];
        nums[from] = nums[partitionIndex];
        nums[partitionIndex] = tmp;

        return partitionIndex;


    }

    private static int partition2(int[] nums, int from, int end) {
        int currentStandard = nums[from];

        int headIndex = from +1;
        int tailIndex = end;

        while(headIndex < tailIndex){
            while(nums[headIndex] < currentStandard && headIndex <= end){
                headIndex++;
            }
            while(nums[tailIndex] > currentStandard && tailIndex >= from+1){
                tailIndex--;
            }

            if(headIndex > tailIndex){
                break;
            }

            if(tailIndex > headIndex){
                int tmp = nums[tailIndex];
                nums[tailIndex] = nums[headIndex];
                nums[headIndex] = tmp;
            }
            headIndex ++;
            tailIndex --;
        }

        int tmp = nums[tailIndex];
        nums[tailIndex] = nums[from];
        nums[from] = tmp;

        return tailIndex;

    }



}
