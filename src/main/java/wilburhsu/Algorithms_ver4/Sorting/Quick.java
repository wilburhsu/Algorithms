package wilburhsu.Algorithms_ver4.Sorting;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 算法2.5 快速排序 P182
 * */

public class Quick {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if(hi <= lo)
            return;
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static int partition(Comparable[] a,int lo,int hi){
        int i = lo,j = hi + 1;
        Comparable v = a[lo];
        for(;;) {
            while (less(a[++i],v))
                if(i == hi)
                    break;
            while (less(v,a[--j]))
                if(j == lo)
                    break;
            if(i >= j)
                break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    private static boolean less(Comparable v,Comparable w) {
        return v.compareTo(w)<0;
    }

    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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
        Integer[] a = {39,878,466,9,5,8,7,40,545,4,9,65765,5478,313};
        sort(a);
        if(isSorted(a)){
            System.out.println("已排序");
        }else {
            System.out.println("排序不成功");
        }
        show(a);
    }

}
