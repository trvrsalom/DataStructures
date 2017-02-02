import java.util.LinkedList;
import java.util.EmptyStackException;

public class StackTester_Salom {
	public static void main(String[] args) {
		Stack<String>dishes = new Stack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()) {
			System.out.println("Popping: "+dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()) {
			System.out.println("Popping: " +dishes.pop());
		}
	}
}

/**
 * The Stack class represents a last-in-first-out (LIFO) stack of objects. It extends class Vector with five operations that allow a vector to be treated as a stack. The usual push and pop operations are provided, as well as a method to peek at the top item on the stack, a method to test for whether the stack is empty, and a method to search the stack for an item and discover how far it is from the top.
 */
class Stack<E> {
	private LinkedList<E>list;
	private int size;

	public Stack() {
		list = new LinkedList<E>();
		size = 0;
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * @param E element to add
	 */
	public void push(E element) {
		list.addFirst(element);
		size++;
	}

	/**
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return element
	 */
	public E pop() {
		if(isEmpty()) throw new EmptyStackException();
		size--;
		return list.removeFirst();
	}

	/**
	 * Tests if this stack is empty.
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the stack.
	 * @return the top element
	 */
	public E peek() {
		if(isEmpty()) throw new EmptyStackException();
		return list.getFirst();
	}
}
