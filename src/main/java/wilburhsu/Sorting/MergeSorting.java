package wilburhsu.Sorting;

import java.util.Arrays;

/**
 *归并排序
 **/
public class MergeSorting {
    public static void main(String[] args) {
        Comparable[] arr = {9,8,7,6,5,4,3,2,1,0};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void sort(Comparable[] arr){
        Comparable[] temp = new Comparable[arr.length];
        sort(arr,0,arr.length-1,temp);
    }

    private static void sort(Comparable[] arr,int left,int rigth,Comparable[] temp){
        if(left < rigth){
            int mid = (left + rigth)/2;
            sort(arr,left,mid,temp);
            sort(arr,mid + 1,rigth,temp);

        }
    }

    private static void merge(Comparable[] arr,int left,int mid,int right, Comparable[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;
        while(i < mid && j <= right){

        }

    }
}
