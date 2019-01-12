package wilburhsu.DSandANinJava;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType>{
    private static class Node<AnyType>{
        public Node(AnyType d,Node<AnyType> p, Node<AnyType> n){
            data = d;
            prev = p;
            next = n;
        }

        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;
    }

    public MyLinkedList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private  void doClear(){
        beginMarker = new Node<AnyType>(null,null,null);//构造一个空的首节点
        endMarker = new Node<AnyType>(null,beginMarker,null);//构造一个尾节点，前置节点为首节点
        beginMarker.next = endMarker;//将首节点的后置节点置为尾节点

        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x){
        addBefore(getNode(idx, 0, size()), x);
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal){
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    private void addBefore(Node<AnyType> p,AnyType x){
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    private Node<AnyType> getNode(int idx){
        return getNode(idx, 0 , size() - 1);
    }

    private Node<AnyType> getNode(int idx,int lower,int upper){
        Node<AnyType> p;
        if(idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();

        if(idx < size() / 2){
            p = beginMarker.next;
            for(int i = 0; i < idx;i++)
                p = p.next;
        }else {
            p = endMarker;
            for(int i = size(); i > idx; i--)
                p = p.prev;
        }
        return p;
    }

    public Iterator<AnyType> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType>{
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext(){
            return current != endMarker;
        }

        public AnyType next(){
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}
