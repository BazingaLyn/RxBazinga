package org.lyncc.bazinga.rx.bazinga.algorithm.leetcode;

public class Solution136 {


    public static void main(String[] args) {
        int[] nums = new int[]{4,1,2,1,2,6,6,4,5};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        if(nums.length == 0) return 0;

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
           result ^= nums[i];
        }

        return result;
    }
}
