package wilburhsu.Algorithms_ver4.Sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 算法2.2  插入排序 P157
 * */

public class Insertion {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 1;i<N;i++){//将a[i]插入到a[i-1]、a[i-2]、a[i-3]...中
            for(int j = i; j > 0 && less(a[i],a[j-1]); j--)
                exch(a, j, j-1);
        }
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = i;
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a){
        for(int i = 0;i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a){
        for(int i = 0; i< a.length; i++){
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        String[] a = In.readStrings();
        insertion.sort(a);
        insertion.show(a);
    }
}
