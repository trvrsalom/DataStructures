import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class DoublyLinkedList_TrevorSalom {
	public static void main(String[] args) {
		DoublyLinkedList <Integer>list = new DoublyLinkedList <Integer>();
		list.add(1, 0);
		list.addFirst(2);
		System.out.println(list);
		list.add(3, 1);
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
		list.add(5);
		list.add(9);
		list.add(10);
		list.add(11);
		System.out.println(list);
		list.reverse();
		System.out.println(list);
	}
}

class DoublyLinkedList <E> {
	public Node first;
	public Node last;
	private int size = 0;

	private class Node {
		public E data;
		public Node next;
		public Node previous;

		public Node(E data) {
			this.data = data;
		}

		public Node(E data, Node next, Node previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		public String toString() {
			return data.toString();
		}

		public Node next() {
			return next;
		}

		public Node previous() {
			return previous;
		}
	}

	public E getFirst() {
		if(first == null) throw new NoSuchElementException();
		return first.data;
	}

	public E removeFirst() {
		if(first == null) throw new NoSuchElementException();
		Node node = first;
		first = node.next;
		first.previous = null;
		size--;
		return node.data;
	}

	public void addFirst(E e) {
		Node node = new Node(e, first, null);
		size++;
		first = node;
	}

	public void addLast(E e) {
		Node node = new Node(e, null, last);
		size++;
		last.next = node;
		last = node;
	}

	public E removeLast() {
		if(last == null) throw new NoSuchElementException();
		Node node = last;
		last = last.previous;
		last.next = null;
		size--;
		return node.data;
	}

	public E get(int index) {
		if(first == null) throw new NoSuchElementException();
		int i = 1;
		Node node = first;
		while(i < index) {
			i++;
			node = node.next;
		}
		return node.data;
	}

	public void add(E element, int index) {
		if(index == 0) {
			addFirst(element);
		}
		else if(index == size) {
			addLast(element);
		}
		else {
			Node before = first;
			Node node = new Node(element);
			for(int i = 0; i < index - 1; i++) {
				before = before.next;
			}
			before.next.previous = node;
			node.next = before.next;
			before.next = node;
			node.previous = before;
			size++;
		}
	}

	public void add(E element) {
		add(element, size);
	}

	public E remove(int index) {
		if(first == null) throw new NoSuchElementException();
		int i = 0;
		Node node = first;
		while(i < index && index != 0) {
			i++;
			node = node.next;
		}
		if(i != 0) node.previous.next = node.next;
		else first = node.next;
		if(i != size - 1) node.next.previous = node.previous;
		else last = node.previous;
		size--;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void reverse() {
		Node itr = first;
		Node temp = first;
		first = last;
		last = temp;
		while(itr != null) {
			temp = itr.next;
			itr.next = itr.previous;
			itr.previous = temp;
			itr = temp;
		}
	}

	public String toString() {
		Node node = first;
		String str = node.data.toString();
		while(node.next != null) {
			node = node.next;
			str += ", " + node.data.toString();
		}
		return str;
	}
}
