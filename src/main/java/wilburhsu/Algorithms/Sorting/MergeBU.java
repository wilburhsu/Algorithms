package wilburhsu.Algorithms.Sorting;

import edu.princeton.cs.algs4.StdOut;

/**
 * 自底向上的归并排序 P175
 * */

public class MergeBU {
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1;sz < N;sz = sz + sz)
            for (int lo = 0;lo < N-sz; lo+=sz+sz)
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
    }


    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i = lo,j = mid + 1;
        for(int k = lo; k < a.length; k++)
            aux[k] = aux[k];

        for(int k = lo; k < hi; k++)
            if(i > mid)
                a[k] = aux[j++];
            else if(j>hi)
                a[k] = aux[i++];
            else if (less(aux[i],aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];

    }

    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i<a.length; i++)
            if(a[i].compareTo(a[i-1]) < 0)
                return false;
        return true;
    }

    private static void show(Comparable[] a){
        for(int i = 0;i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
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
