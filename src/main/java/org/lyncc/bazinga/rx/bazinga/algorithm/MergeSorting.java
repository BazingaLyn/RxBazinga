package org.lyncc.bazinga.rx.bazinga.algorithm;

/**
 * 合并排序
 *
 * @author liguolin
 * @create 2018-02-01 11:18
 **/
public class MergeSorting {

    public static void main(String[] args) {

//        System.out.println(1/2);

        int[] nativeList = new int[]{21,33,2,5,99,6,65,5436,23,44,67,9,0};

        mergeSort(nativeList);

        for(int m = 0;m < nativeList.length;m++){
            System.out.println(nativeList[m]);
        }

    }

    private static void mergeSort(int[] array) {

        int []temp = new int[array.length];
        sort(array,0,array.length-1,temp);
    }

    private static void sort(int[] array, int left, int right, int[] temp) {
        if(left<right){
            int mid = (left+right)/2;
            sort(array,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(array,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(array,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
}
