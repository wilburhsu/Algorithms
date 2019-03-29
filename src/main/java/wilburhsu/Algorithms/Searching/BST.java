package wilburhsu.Algorithms.Searching;

import wilburhsu.Algorithms.Fundamentals.Queue;

/**
 * 算法3.3 基于二叉查找树的符号表 P252
 * */

public class BST <Key extends Comparable<Key>,Value>{
    private Node root; //根结点
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int N; //以该结点为根的子树中的结点总数

        public Node(Key key,Value val,int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x == null)
            return 0;
        else
            return x.N;
    }

    /*二叉查找树的查找和排序方法的实现 P252*/
    public Value get(Key key){
        return get(root,key);//该方法从根结点开始查找
    }

    private Value get(Node x,Key key){
        //在以x为根结点的子树中查找并返回key所对应的值
        //如果找不到则返回null
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return get(x.left,key);
        else if(cmp > 0)
            return get(x.right,key);
        else
            return x.val;
    }

    public void put(Key key,Value val){
        //查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root,key,val);
    }

    private Node put(Node x,Key key,Value val){
        //如果key存在于以x为根结点的子树中则更新它的值
        //否则将以key和val为键值对的新结点插入到该子树中
        if(x == null)
            return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left,key,val);
        if(cmp > 0)
            x.right = put(x.right,key,val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    /*二叉查找树中max()、min()、floor()、ceiling()方法的实现*/
    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null)
            return x;
        return min(x.left);
    }

    public Key floor(Key key){
        Node x = floor(root,key);
        if(x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x,Key key){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp < 0)
            return floor(x.left,key);
        else if(cmp > 0)
            return floor(x.right,key);
        Node t = floor(x.right,key);
        if(t != null)
            return t;
        else
            return x;
    }

    /*二叉查找树中select()和rank()方法的实现*/
    public Key select(int k){
        return select(root,k).key;
    }

    //找到排名为k的键（即树中正好有k个小于它的键）
    private Node select(Node x,int k){
        if(x == null)
            return null;
        int t = size(x.left);//获得左子树的结点数t
        if(t > k)
            return select(x.left,k);//如果t>k，那么继续在左子树中（递归）查找排名为k的键
        else if(t < k)
            return select(x.right,k-t-1);//如果t<k，那么在右子树中（递归）查找排名为（k-t-1）的键
        else
            return x;
    }

    public int rank(Key key){
        return rank(key,root);
    }

    //返回以x为根结点的子树中小于x.key的键的数量
    private int rank(Key key,Node x){
        if(x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return rank(key,x.left);
        else if(cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    /*二叉查找树的delete()方法的实现*/
    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){//返回被删除结点的右链接，被删除结点的左链接为空
        if(x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        root = delete(root,key);
    }

    private Node delete(Node x,Key key){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = delete(x.left,key);//向左子树中递归删除调用
        else if(cmp > 0)
            x.right = delete(x.right,key);//向右子树中递归删除调用
        else {//x.key等于当前搜索到的结点的key值，即找到了要删除的结点
            if(x.right == null)//要删除的结点右链接为空，即当前结点只有左链接，则返回左链接
                return x.left;
            if(x.left == null)//要删除的结点左链接为空，即当前结点只有右链接，则返回右链接
                return x.right;
            //被删除的结点有左右两个链接
            Node t = x;//step1：将指向即将被删除的结点的链接保存为t
            x = min(t.right);//step2：取t的右子树，然后不断检查左子树，找到要被删除的结点的右子树中的最小结点，并将x指向该最小结点，此时x的左链接为空
            x.right = deleteMin(t.right);//step3：删除找到的t结点右子树中的最小结点
            x.left = t.left;//step4：将右子树中最小结点的左链接（本为空）设为t.left
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /*二叉查找树的范围查找操作*/
    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0)//查找左子树
            keys(x.left,queue,lo,hi);
        if(cmplo <= 0 && cmphi >= 0)//查找根结点
            queue.enqueue(x.key);
        if(cmphi > 0)//查找右子树
            keys(x.right,queue,lo,hi);
    }

}
