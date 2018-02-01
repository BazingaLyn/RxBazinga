package org.lyncc.bazinga.rx.bazinga.algorithm;

/**
 * 快速排序
 *
 * @author liguolin
 * @create 2018-02-01 14:22
 **/
public class QuickSorting {

    public static void main(String[] args) {
        int[] arrays = new int[]{15,3,11,2,6,9,10,1,13,19,18,20,12,8,4,3,27,-1,99};

        quickSort(arrays,0,arrays.length-1);

        for(int i = 0; i < arrays.length;i++){
            System.out.println(arrays[i]);
        }
    }

    private static void quickSort(int[] arrays, int low, int high) {
        int tempvalue;

        if(low > high) return;

        int standardValue = arrays[low];

        int i= low;
        int j= high;

        while(i < j){

            while(arrays[j] >= standardValue && i < j){
                j--;
            }

            while(arrays[i] <= standardValue && i < j){
                i++;
            }

            if(i < j){
                tempvalue = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] =tempvalue;
            }
        }

        arrays[low] = arrays[i];
        arrays[i] = standardValue;

        //递归调用左半数组
        quickSort(arrays, low, j-1);
        //递归调用右半数组
        quickSort(arrays, j+1, high);

    }
}
