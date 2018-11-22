package wilburhsu.Algorithms.Fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 算法1.2 下压堆栈（链表实现）
 * */

public class Stack<Item> implements Iterable<Item>{
	private class Node{
		Item item;
		Node next;
	}

	private Node first;
	private int N;

	public boolean isEmpty(){
		return first == null;
	}
	public int size(){
		return N;
	}
	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop(){
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator(){
		return null;
	}
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		public void remove(){

		}
		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Stack<String> s= new Stack<String>();
		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				StdOut.print(s.pop() + "");
		}
		StdOut.println("(" + s.size() + "left on stack)");
	}

}
