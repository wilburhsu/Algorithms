package wilburhsu.Algorithms.Sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 算法2.1 选择排序 P156
 * */
public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length;//数组长度
        for(int i = 0; i < N; i++){//将a[i]和a[i+1...N中最小的元素交换]
            int min = i;
            for(int j = i+1; j < N; j++)
                if(less(a[j],a[min])) //如果当前元素小于当前最小元素
                    min = j;
            exch(a, i ,min);
        }
    }

    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a,int i,int j){
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
        for (int i = 1; i < a.length; i++)
            if(less(a[i],a[i-1])) //如果后一个元素小于其前一个元素，排序不成功
                return false;
        return true;
    }

    public static void main(String[] args) {
        Selection selection = new Selection();
        String[] a = In.readStrings();
        selection.sort(a);
        selection.show(a);
    }
}
