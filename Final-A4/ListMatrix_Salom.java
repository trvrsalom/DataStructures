public class ListMatrix_Salom {
	public static void main(String[] args) {
		ListIntMatrix test = new ListIntMatrix(5, 5);
		System.out.println(test.getRowNum());
		System.out.println(test.getColNum());
		for (int i = 0; i < test.getRowNum(); i++) {
			for (int j = 0; j < test.getRowNum(); j++) {
				test.set(i, j, i+j);
			}
		}
		test.print();

		ListIntMatrix test2= new ListIntMatrix(5, 5);
		System.out.println(test2.getRowNum());
		System.out.println(test2.getColNum());
		for (int i = 0; i < test2.getRowNum(); i++) {
			for (int j = 0; j < test2.getRowNum(); j++) {
				test2.set(i, j, i-j);
			}
		}
		test2.print();

		test.add(test2);
		System.out.println("adding");
		test.print();
	}
}

interface MyList<T>extends Iterable {
	public boolean add(T value);
	public boolean add(int index, T value); public T remove(int index);
	public void clear();
	public boolean replace(int index, T value); public T getEntry(int index);
	public boolean contains(T value);
	public int size();
	public boolean isEmpty();
	public boolean isFull();
}

interface IntMatrix {
	public boolean set(int row, int col, Integer new_val);
	public Integer get(int row, int col);
	public int getRowNum();
	public int getColNum();
	public boolean add(IntMatrix addee);
	public void print();
}

class ListIntMatrix implements IntMatrix {
	private Integer[][] matrix;
	public ListIntMatrix()
	{
		this(1,1);
	}
	public ListIntMatrix(int row_num, int col_num) {
		matrix = new Integer[row_num][col_num];
	}
	public Integer get(int row, int col) {
		return matrix[row][col];
	}
	public int getRowNum() {
		return matrix.length;
	}
	public int getColNum() {
		return matrix[0].length;
	}
	public boolean add(IntMatrix addee) {
		if(getRowNum() != addee.getRowNum() || getColNum() != addee.getColNum()) return false;
		for(int r = 0; r < getRowNum(); r++)  {
			for(int c = 0; c < getRowNum(); c++) {
				matrix[r][c] += addee.get(r, c);
			}
		}
		return true;
	}
	public boolean set(int row, int col, Integer newVal) {
		if(row >= getRowNum() || col >= getColNum()) return false;
		matrix[row][col] = newVal;
		return true;
	}
	public void print() {
		for(int r = 0; r < getRowNum(); r++) {
			for(int c = 0; c < getRowNum(); c++) System.out.print(matrix[r][c] + ", ");
			System.out.println("");
		}
	}
}
