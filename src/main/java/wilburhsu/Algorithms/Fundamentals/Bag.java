package wilburhsu.Algorithms.Fundamentals;

import java.util.Iterator;

/**
 * 算法1.4 背包 P98
 * */

public class Bag<Item> implements Iterable<Item>{
    private Node first;//链表首节点

    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
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
