package wilburhsu.Algorithms.Fundamentals;

import java.util.Iterator;

/**
 *算法1.3 先进先出队列 P95
 * */

public class Queue <Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){//向队列尾部添加元素
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())//如果队列为空，队列尾部插入节点后首节点和尾节点相同
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public Item dequeue(){//从队列头部删除元素
        Item item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }

        public void remove(){ }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
