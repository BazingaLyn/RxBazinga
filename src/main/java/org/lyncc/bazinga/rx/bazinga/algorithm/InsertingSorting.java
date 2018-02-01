package org.lyncc.bazinga.rx.bazinga.algorithm;

/**
 * 插入排序
 *
 * @author liguolin
 * @create 2018-02-01 9:55
 **/
public class InsertingSorting {

    public static void main(String[] args) {
        int[] nativeList = new int[]{21,33,2,5,99,6,65,5436,23,44,67,9,0};

        nativeList = insertingSort(nativeList);

        for(int m = 0;m < nativeList.length;m++){
            System.out.println(nativeList[m]);
        }

    }

    public static int[] insertingSort(int[] list){

        for(int i = 0 ;i < list.length ; i++){

            int temp = list[i];

            for(int j = i - 1; j >= 0; j--){
                if(list[j] > temp){
                    int tempj = list[j];
                    list[j] = temp;
                    list[j+1] = tempj;
                }
            }
        }

        return list;

    }


//    public static int[] insertingSort(int[] list){
//
//        int i,j;
//
//        for(i = 1;i < list.length;i++){
//
//           int temp = list[i];
//
//           j = i - 1;
//
//           while(j >= 0 && temp < list[j]){
//               list[j+1] = list[j];
//               j--;
//           }
//            list[j + 1] = temp;
//        }
//        return list;
//    }

}
