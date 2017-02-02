import java.util.NoSuchElementException;

public class StackArrayRunner_Salom
{
	public static void main(String[] args)
	{
		StackArray sa = new StackArray();
		sa.push("a");
		sa.push("b");
		sa.push("c");
		sa.push("d");
		sa.push("e");
		System.out.println(sa);
		System.out.println(sa.pop());
		System.out.println(sa);
		System.out.println(sa.pop());
		System.out.println(sa);
		System.out.println(sa.pop());
		System.out.println(sa);
		System.out.println(sa.pop());
		System.out.println(sa);
		System.out.println(sa.pop());
		System.out.println(sa);
		System.out.println(sa.pop());
	}
}

class StackArray
{
	private Object[] item; // The array where elements are stored
	private int open = 0; // The index of the first empty location in the stack
	private int size = 2; // The current number of item locations in the stack

	/**
	   Constructs an empty stack.
	 */
	public StackArray()
	{
		item = new Object[size];
	}

	public void push(Object element)
	{
		if(open == size - 1) {
			size *= 2;
			Object[] newItems = new Object[size];
			for(int i = 0; i < item.length; i++) newItems[i] = item[i];
			item = newItems;
		}
		item[open++] = element;
	}

	public Object pop()
	{
		if(isEmpty()) throw new NoSuchElementException();
		else return item[--open];
	}

	public boolean isEmpty()
	{
		return open == 0;
	}

	public String toString()
	{
		if (open == 0) { return "[]"; }
		String temp = "[" + item[0];
		int i = 1;
		while (i < open)
		{
			temp = temp + ", " + item[i];
			i = i + 1;
		}
		temp = temp + "]";
		return temp;
	}
}
