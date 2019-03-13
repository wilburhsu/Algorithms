package wilburhsu.Algorithms.Sorting;

/**
 * 算法2.7 堆排序 P206
 * */

public class HeapSort{
    //private static Comparable[] a;

    public void heapSort(Comparable[] a){
        int N = a.length;
        /*for循环构造一个堆，在构造有序堆时，开始只需要扫描一半的元素（N/2~ 1）即可，
        因为只有（N/2~ 1）的结点才有子结点（根结点索引为1）*/
        for(int k = N/2; k >= 1;k--){
            sink(a, k, N);//从最后一个非叶子结点开始，往堆顶端对每个结点依次进行下沉操作，直到达到顶端根结点
        }
        while (N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

//    public void sink(Comparable[] a,int i, int j){
//        while (2*i < j){
//            int k = 2 * i;
//            if(k < j && less(k, k+1))
//                k++;
//            if(less(k,i))
//                break;
//            exch(a,i,k);
//        }
//    }

    //下沉操作，如果当前结点比它的两个子结点都大，则将其与两个子结点中较大的一个进行交换
    private void sink(Comparable[] a,int k,int n){
        while(2*k <= n){
            int j = 2*k;//此时j=2k
            /*如果满足a[2k]>a[2k+1]，即左子结点大于右子结点，此时j=2k，j指向左子结点；
            如果pq[2k]<pq[2k+1]，即左子结点小于右子结点，那么j=2k+1，即j指向右子结点*/
            if(j < n && less(a,j,j+1))
                j++;
            if(!less(a,k,j))
                break;
            exch(a,k,j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a,int i,int j){
       return  a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
