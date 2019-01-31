package wilburhsu.Algorithms.Sorting;

import edu.princeton.cs.algs4.StdOut;

/**
 * 算法2.4 自顶向下的归并排序 P171
 * */

public class Merge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length - 1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if(hi <= lo)
            return;
        int mid = lo + (hi - lo)/2;
        sort(a,lo,mid);
        sort(a,mid + 1,hi);
        merge(a,lo,mid,hi);
    }

    public static void merge(Comparable[] a,int lo, int mid,int hi){
        int i = lo,j = mid+ 1;

        for(int k = lo; k<=hi; k++)
            aux[k] = a[k];//将要排序的数据复制到临时数组aux

        for(int k = lo;k <= hi; k++)
            if(i > mid)
                a[k] = aux[j++];//如果左边的元素已经遍历完，那么将右边数组剩下的元素一次复制到原来的数组
            else if(j > hi)
                a[k] = aux[i++];
            else if(less(aux[j],aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
    }

    private static boolean less(Comparable v,Comparable w) {
        return v.compareTo(w)<0;
    }

    private static void show(Comparable[] a){
        for(int i = 0;i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1;i<a.length;i++)
            if(less(a[i],a[i-1]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {39,878,466,9,5,8,7,40,545,4,65765,5478,313};
        sort(a);
        if(isSorted(a)){
            System.out.println("已排序");
        }else {
            System.out.println("排序不成功");
        }
        show(a);
    }
}