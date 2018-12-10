package org.lyncc.bazinga.rx.bazinga.leetcode;

/**
 * @author liguolin
 * @create 2018-12-09 17:03
 **/
public class Problems1 {


    public static void main(String[] args) {

        Problems1 problems1 = new Problems1();
        int[] ints = problems1.twoSum(new int[]{3,2,4}, 6);
        System.out.println(ints[0]+" "+ints[1]);
    }

    public int[] twoSum(int[] nums, int target) {

        for(int i = 0;i < nums.length;i++){
            for(int j = 0;j < nums.length;j++){
                if(nums[i]+nums[j] == target && nums[i] != nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
}
