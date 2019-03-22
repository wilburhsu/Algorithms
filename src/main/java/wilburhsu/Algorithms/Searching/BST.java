package wilburhsu.Algorithms.Searching;

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

    /*二叉查找树中selec()和rank()方法的实现*/
    public Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node x,int k){//找到排名为k的键（即树中正好有k个小于它的键）
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

    private int rank(Key key,Node x){
        //返回以x为根结点的子树中小于x.key的键的数量
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
    //Todo


    /*二叉查找树的范围查找操作*/
    //Todo



}
