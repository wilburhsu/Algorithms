package wilburhsu.Algorithms.Sorting;

/**
 *算法2.6 基于堆的优先队列 P202
 *
 * */

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];//从根节点得到最大元素
        exch(1,N--);//将其和最后一个结点交换，执行这一步后当前N值为N-1
        pq[N+1] = null;//防止越界
        sink(1);//恢复堆的有序性
        return max;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k){
        while (k > 1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    //下沉操作，如果当前结点比它的两个子结点都大，则将其与两个子结点中较大的一个进行交换
    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;//此时j=2k
            /*如果满足pq[2k]>pq[2k+1]，即左子结点大于右子结点，此时j=2k，j指向左子结点；
            如果pq[2k]<pq[2k+1]，即左子结点小于右子结点，那么j=2k+1，即j指向右子结点*/
            if(j<N && less(j,j+1))
                j++;
            if(!less(k,j))
                break;
            exch(k,j);
            k = j;
        }
    }

}
