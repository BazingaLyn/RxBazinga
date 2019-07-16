package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] nums) {


        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,3,7,2};
        mergeSort(nums);
        System.out.println(JSON.toJSONString(nums));
    }


    public static void mergeSort(int[] nums){
        _mergeSort(nums,0,nums.length-1);
    }

    private static void _mergeSort(int[] nums, int left, int right) {
        if(left >= right){
            return;
        }
        int mid = (left + right)/2;
        _mergeSort(nums,left,mid);
        _mergeSort(nums,mid+1,right);
        merge(nums,left,mid,right);
    }

    private static void merge(int[] nums,int left,int mid,int right){
        int[] temp = Arrays.copyOfRange(nums, left, right + 1);

        int leftIndex = left;
        int rightIndex = mid+1;

        for (int i = left; i <= right; i++) {
            if(leftIndex > mid){
                nums[i] = temp[rightIndex-left];
                rightIndex++;
            }else if(rightIndex > right){
                nums[i] = temp[leftIndex -left];
                leftIndex++;
            }else if(temp[leftIndex-left] < temp[rightIndex -left]){
                nums[i] = temp[leftIndex-left];
                leftIndex ++;
            }else{
                nums[i] = temp[rightIndex-left];
                rightIndex ++;
            }

        }

    }

    public static void quickSort(int[] nums){
        _quickSort(nums,0,nums.length-1);
    }

    private static void _quickSort(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        int tmp = nums[left];

        if(i >= j){
            return;
        }

        while(i != j){
            while(nums[j] >= tmp && i < j){
                j--;
            }
            while(nums[i] <= tmp && i < j){
                i++;
            }
            if(i < j){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        int temp = nums[i];
        nums[i] = nums[left];
        nums[left] = temp;
        if(left <= i - 1)
            _quickSort(nums,left,i-1);
        if(right >= i + 1)
            _quickSort(nums,i+1,right);


    }
}
