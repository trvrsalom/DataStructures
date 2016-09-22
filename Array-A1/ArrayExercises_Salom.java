/**
 *
 */

public class ArrayExercises_Salom {
	public static void main(String[] args) {
		printArr(r7_4a());
		int[] test = r7_4b();
		printArr(test);
		r7_5(test);
		print2dArr(r7_28());
		print2dArr(e7_16(5));
		e7_17(new int[] {10, 5, 3, 1});
		e7_18(new int[] {10, -5, 3, 1, 6});
	}

	/**
	 * R7.4: Write a loop that fills an array values with ten random numbers between 1 and 100.
	 */
	public static int[] r7_4a() {
		int[] randomArr = new int[10];
		for(int i = 0; i < 10; i++) {
			randomArr[i] = (int)(Math.random() * 100);
		}
		return randomArr;
	}

	/**
	 * R7.4: Write code for two nested loops that fill values with ten different random numbers between 1 and 100.
	 */
	public static int[] r7_4b() {
		int[] uniqueRandomArr = new int[10];
		for(int i = 0; i < 10; i++) {
			int r = (int)(Math.random() * 100);
			for(int b = 0; b < i; b++) {
				if(uniqueRandomArr[b] == r) {
					r = (int)(Math.random() * 100);
					b = 0;
				}
			}
			uniqueRandomArr[i] = r;
		}
		return uniqueRandomArr;
	}

	/**
	 * R7.5: Write Java code for a loop that simultaneously computes both the maximum and minimum of an array
	 */
	public static void r7_5(int[] arr) {
		int max = arr[0];
		int min = arr[0];
		for(int i : arr) {
			max = i > max ? i : max;
			min = i < min ? i : min;
		}
		System.out.println("Max: " + max + ", Min: " + min);
	}

	/**
	 * A run is a sequence of adjacent repeated values. Give pseudocode for computing
	 * the length of the longest run in an array. For example, the longest run in
	 * the array with elements
	 *
	 * runLen = 0
	 * currRun = arr[0]
	 * for each element of arr except the first
	 * 	if element = currRun
	 * 	 runLen++
	 * 	else
	 * 	 runLen = 0
	 *  currRun = element
	 * return runLen
	 */

	/**
	 * R7.28 Fill only the elements in the top and bottom rows with zeroes.
	 */
	public static int[][] r7_28() {
		int[][] values = new int[10][10];
		for(int i = 0; i < values[0].length; i++) {
			values[0][i] = 0;
			values[values.length - 1][i] = 0;
		}
		return values;
	}

	/**
	 * E7.16: Implement the following algorithm to construct magic n*n squares;
	 * it works only if n is odd.
	 */
	public static int[][] e7_16(int n) {
		int row = n - 1, column = n/2;
		int[][] magic = new int[n][n];
		for(int k = 1; k <= n*n; k++) {
			magic[row][column] = k;
			row++; column++;
			int lastRow = row, lastColumn = column;
			row = row == n ? 0 : row;
			column = column == n ? 0 : column;
			if(magic[row][column] > 0) {
				row = lastRow;
				column = lastColumn;
				row--;
			}
		}
		return magic;
	}

	/**
	 * Write a program that reads a sequence of input values and displays a bar
	 * chart of the values, using asterisks
	 */
	public static void e7_17(int[] vals) {
		int maxVal = vals[0];
		for(int i : vals) maxVal = i > maxVal ? i : maxVal;
		String starStr = "";
		for(int i = 0; i < 40; i++) starStr += '*';
		for(int i : vals) System.out.println(starStr.substring(0, (int)(40.0*((double)i/(double)maxVal))));
	}

	/**
	 * Improve the program of ExerciseE7.17 to work correctly when the data set
	 * contains negative values.
	 */
	public static void e7_18(int[] vals) {
		int maxVal = vals[0];
		int minVal = vals[0];
		for(int i : vals) {
			maxVal = i > maxVal ? i : maxVal;
			minVal = i < minVal ? i : minVal;
		}
		int absMax = Math.abs(maxVal) > Math.abs(minVal) ? maxVal : minVal;
		String starStr = "";
		String spaceStr = "";
		for(int i = 0; i < 40; i++) {
			starStr += '*';
			spaceStr += ' ';
		}
		for(int i : vals) {
			int stars = (int)(40*((double)i/(double)absMax));
			System.out.println(
				spaceStr.substring(0, 40 - (stars < 0 ? Math.abs(stars) : 0)) +
				starStr.substring(0, (stars < 0 ? Math.abs(stars) : 0)) +
				"|" +
				starStr.substring(0, (stars > 0 ? stars : 0))
			);
		}
	}

	public static void printArr(int[] arr) {
		for(int i : arr)
			System.out.print(i + ", ");
		System.out.println(" ");
	}

	public static void print2dArr(int[][] arr) {
		for(int[] a : arr) {
			printArr(a);
		}
	}
}
