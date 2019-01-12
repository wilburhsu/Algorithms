package wilburhsu.DSandANinJava;

import java.util.Iterator;

public class  MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;//默认容量大小
    private int theSize;//当前容量大小
    private AnyType [ ] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity){
        if (newCapacity < theSize)
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType [ ]) new Object[newCapacity];
        for(int i = 0; i<size(); i++)
            theItems[i] = old[i];
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx,AnyType x){
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for(int i = theSize;i > idx; i--){
            theItems[i] = theItems[i - 1];
        theItems[idx] = x;
        theSize++;
        }
    }

    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];
        for(int i = idx;i < size() - 1;i++ )
            theItems[i] = theItems[i+1];

        theSize--;
        return  removedItem;
    }

    public Iterator<AnyType> iterator(){
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        private int current = 0;
        private boolean okToRemove = false;
        public boolean hasNext(){
            return current < size();
        }

        public AnyType next(){
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            okToRemove = true;
            return theItems[current++];
        }

        public void remove(){
            if( !okToRemove )
                throw new IllegalStateException( );
            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }
}


class TestArrayList
{
    public static void main( String [ ] args )
    {
        MyArrayList<Integer> lst = new MyArrayList<>( );

        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );
    }
}
