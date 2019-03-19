package wilburhsu.Algorithms.Searching;

import wilburhsu.Algorithms.Fundamentals.Queue;

/**
 * 算法3.2 二分查找（基于有序数组）P239
 * */

public class BinarySearchST<Key extends Comparable <Key>,Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public Value get(Key key){
        if(isEmpty())
            return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    private boolean isEmpty(){
        return size() == 0;
    }

    /*Todo 基于有序数组的二分查找(迭代) P241*/
    public int rank(Key key){
        int lo = 0,hi = N - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
                hi = mid - 1;
            else if(cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key,Value val){
        //查找键，找到则更新值，否则创建新的元素
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        //在位置i插入元素，从i开始，将所有更大的键向后移动一位（从N到i，由后向前，依次移动）
        for(int j = N; j > i;j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /*Todo 基于二分查找的有序符号表的其他操作*/
    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        return keys[k];
    }

    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){

        return  null;
    }

    public Key delete(Key key){

        return null;
    }

    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> q = new Queue<>();
        for(int i = rank(lo);i < rank(hi); i++)
            q.enqueue(keys[i]);
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public boolean contains(Key key){
        if(key == null)
            throw new IllegalArgumentException("key is null in function contain");
        return get(key) != null;
    }
}
