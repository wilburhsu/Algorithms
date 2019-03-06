package wilburhsu.Algorithms.Sorting;

public class HeapSort{
    private static Comparable[] a;

    public void heapSort(Comparable[] a){
        int N = a.length;
        for(int k = N/2; k >= 1;k--){
            sink(a, k, N);
        }
        while (N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public void sink(Comparable[] a,int i, int j){
        while (2*i < j){
            int k = 2 * i;
            if(k < j && less(k, k+1))
                k++;
            if(less(k,i))
                break;
            exch(a,i,k);
        }
    }

    private static boolean less(int i,int j){
       return  a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
