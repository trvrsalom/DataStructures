import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QTester_Salom {
	public static void main(String[] args) {
		BasicQueue q = new BasicQueue<String>();

		System.out.println("Q is empty: "+ q.isEmpty());
		System.out.println("Now adding H, E, L, L, O, B, C, P");
		q.add("H");
		q.add("E");
		q.add("L");
		q.add("L");
		q.add("O");
		q.add("B");
		q.add("C");
		q.add("P");
		System.out.println("The top element is: "+ q.peek());
		while (!q.isEmpty()) {
			System.out.println("removing: "+q.remove());
		}
		System.out.println("Is it empty: "+ q.isEmpty());
		System.out.println("Now adding 1");
		q.add("1");
		System.out.println("Is it empty: "+ q.isEmpty());
		System.out.println("Now adding 2, 3, 4, 5");
		q.add("2");
		q.add("3");
		q.add("4");
		q.add("5");
		System.out.println("The top element is: "+ q.peek());
		System.out.println("Removing " + q.remove() );
		System.out.println("Removing "+ q.remove() );
		System.out.println("Now adding 7, 8, 9, 10, 11, 12");
		q.add("7");
		q.add("8");
		q.add("9");
		q.add("10");
		q.add("11");
		q.add("12");
		System.out.println("Now adding Last");
		q.add("Last");
		System.out.println("The top element is: "+ q.peek());
		System.out.println("The size is: "+ q.size());

		while (!q.isEmpty()) {
			System.out.println("removing:" +q.remove());
		}
		for(int i = 0; i < 10000; i++ )
			q.add( "" + i );

		while (!q.isEmpty()) {
			q.remove();
		}

		String str = "*********This is my tester code*********";
		for(char a : str.toCharArray()) q.add(a);
		while(!q.isEmpty()) System.out.print(q.remove() + " ");
	}
}

class BasicQueue <E> {
	private final int INITIAL_SIZE = 10;
	private int size;
	private E[] arr;

	/**
	 * Constructs the BasicQueue
	 */
	public BasicQueue() {
		arr = (E[])new Object[INITIAL_SIZE];
		size = 0;
	}

	/**
	 * Checks if a queue is empty
	 *
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Inserts the specified element into this queue if it is possible to do so
	 * immediately without violating capacity restrictions, returning
	 * <tt>true</tt> upon success and throwing an <tt>IllegalStateException</tt>
	 * if no space is currently available.
	 *
	 * @param e the element to add
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 * @throws IllegalStateException if the element cannot be added at this
	 *         time due to capacity restrictions
	 * @throws ClassCastException if the class of the specified element
	 *         prevents it from being added to this queue
	 * @throws NullPointerException if the specified element is null and
	 *         this queue does not permit null elements
	 * @throws IllegalArgumentException if some property of this element
	 *         prevents it from being added to this queue
	 */
	public boolean add(E element) {
		if(size >= arr.length) {
			E[] temp = (E[])new Object[arr.length * 2];
			for(int i = 0; i < arr.length; i++) {
				temp[i] = arr[i];
			}
			arr = temp;
		}
		arr[size++] = element;
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue.  This method differs
	 * from {@link #poll poll} only in that it throws an exception if this
	 * queue is empty.
	 *
	 * @return the head of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E remove() {
		if(size == 0) throw new NoSuchElementException();
		E elm = arr[0];
		for(int i = 1; i < arr.length; i++) arr[i-1] = arr[i];
		size--;
		return elm;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * or returns <tt>null</tt> if this queue is empty.
	 *
	 * @return the head of this queue, or <tt>null</tt> if this queue is empty
	 */
	public E peek() {
		return arr[0];
	}

	/**
	 * Returns the size of the queue
	 *
	 * @return the size of the queue
	 */
	public int size() {
		return size;
	}
}
