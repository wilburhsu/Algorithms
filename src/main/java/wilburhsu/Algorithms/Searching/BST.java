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
        private int N;

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

        return null;
    }

    public void put(Key key,Value val){

    }


}
