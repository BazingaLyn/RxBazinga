package org.lyncc.bazinga.rx.bazinga.algorithm.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class Sort {


    public static void main(String[] args) {
        int[] nums  = new int[]{5,4,8,1,3,9,7};
        mergeSort(nums);
        System.out.println(JSON.toJSONString(nums));

    }

    //147 148 279




    public static void quickSort(int[] nums){
        _quickSort(nums,0,nums.length-1);
    }


    public static void _quickSort(int[] nums,int l,int r){
        int i = l;
        int j = r;
        int tmp = nums[l];

        if(i >= j){
            return;
        }
        while(i!=j){
            while (nums[j] >= tmp && i<j){
                j--;
            }
            while(nums[i]<=tmp&&i<j){
                i++;
            }

            if(i < j){
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
        }

        int t1 = nums[i];
        nums[i] = nums[l];
        nums[l] = t1;

        _quickSort(nums,l,i-1);
        _quickSort(nums,i+1,r);


    }






    //归并排序
    public static void mergeSort(int[] array){
        _mergeSort(array,0,array.length-1);
    }

    public static void _mergeSort(int[] arrays,int from,int end){
        if(from >= end)
            return;

        int middle = (end+from)/2;
        _mergeSort(arrays,from,middle);
        _mergeSort(arrays,middle +1,end);
        merge(arrays,from,middle,end);
    }

    private static void merge(int[] arrays, int from, int middle, int end) {
        int[] temp  = Arrays.copyOfRange(arrays,from,end+1);

                int leftIndex = from;
                int rightIndex = middle + 1;

                for (int i = from; i <= end; i++) {

                    if(leftIndex > middle){
                        arrays[i] = temp[rightIndex -from];
                        rightIndex ++;
                    }

                    else if(rightIndex > end){
                        arrays[i] = temp[leftIndex -from];
                        leftIndex++;
                    } else if(temp[leftIndex -from] < temp[rightIndex -from]){
                        arrays[i] = temp[leftIndex-from];
                        leftIndex ++;
                    }else{
                        arrays[i] = temp[rightIndex-from];
                rightIndex ++;
            }
        }
    }

    /**
     * 外层第一层循环定义一个界限，然后第二层循环以这个界限(包括这个界限)，进行排序，把这个界限插入到这个界限左侧的应该所在的位置
     * @param array
     */
    public static void insertSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(array[j]< array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }else{
                    break;
                }
            }
        }
    }

    public static void selectSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for(int j = i+1;j <nums.length;j++){
                if(nums[minIndex] > nums[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }

        }

    }

    public static void bubbleSort(int[] nums){

        int length = nums.length;
        while(length > 0){
            for (int i = 1; i < length; i++) {
                if(nums[i-1]>=nums[i]){
                    int temp = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = temp;
                }
            }
            length--;
        }

    }

}
