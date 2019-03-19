package wilburhsu.Algorithms.Searching;

/**
 * 算法3.1 顺序查找（基于无序链表）P236
 * */

public class SequentialSearchST<Key,Value> {
    private Node first; //链表首结点

    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key,Value val,Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public Value get(Key key){
            for(Node x = first;x != null;x = x.next)
                if(key.equals(x.key))
                    return x.val;
                return null;
        }

        public void put(Key key,Value val){
            for(Node x = first;x != null; x = x.next)
                if(key.equals(x.key)){
                    x.val = val;
                    return;
                }
            first = new Node(key,val,first); //未命中，新建结点
        }

        //Todo size()方法
        public int size(){

            return 0;
        }

        //Todo key()方法
        public Iterable<Key> key(){

            return null;
        }

        //TODO delete()方法
        public void delete(Key key){
            if(key == null)
                throw new IllegalArgumentException("key s null in function delete");

            Node fakehead = new Node(null,null,first);

            for(Node prev = fakehead;prev.next != null;prev = prev.next)
                if(key.equals(prev.next.key)){
                    prev.next = prev.next.next;
                    break;
                }
            first = fakehead.next;
        }
    }
}
