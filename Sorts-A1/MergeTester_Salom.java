/**
* MergeTester_Salom (class)
*/
public class MergeTester_Salom {
	public static final int ARRAY_DELTA_MIN = 1;
	public static final int ARRAY_DELTA_MAX = 10;
	public static final int ARRAY_MIN_LEN = 900;
	public static final int ARRAY_MAX_LEN = 1100;

 /**
 * main
 * @param args
 * @return void
 */
	public static void main(String[] args) {
		int[] a = genArray();
		int[] b = genArray();
		printArr(a);
		printArr(b);
		int[] m = merge(a, b);
		printArr(m);
	}

 	/**
 	* prints array
 	* @param arr an array
 	* @return void
 	*/
	public static void printArr(int[] arr) { for(int i : arr) System.out.print(i + " "); System.out.println(); }
	/**
	 * returns random int in range
	 * @param minimum value
	 * @param maximum value
	 * @return int
	 */
	public static int randomRange(int minimum, int maximum) { return minimum + (int)(Math.random() * (maximum - minimum + 1)); }

 /**
 * generates a sorted array
 * @return int[]
 */
	public static int[] genArray() {
		int[] a = new int[randomRange(ARRAY_MIN_LEN, ARRAY_MAX_LEN)];
		a[0] = randomRange(ARRAY_DELTA_MIN , ARRAY_DELTA_MAX);
		for(int i = 1; i < a.length; i++) a[i] = a[i-1] + randomRange(ARRAY_DELTA_MIN , ARRAY_DELTA_MAX);
		return a;
	}

 /**
 * returns a merged sorted array
 * @param a sorted array
 * @param b sorted array
 * @return int[]
 */
	public static int[] merge(int[] a, int[] b) {
    int[] answer = new int[a.length + b.length];
    int aI = 0, bI = 0, nI = 0;
	  while (aI < a.length && bI < b.length) {
	    if (a[aI] < b[bI]) answer[nI++] = a[aI++];
	    else answer[nI++] = b[bI++];
	  }
	  while (aI < a.length) answer[nI++] = a[aI++];
		while (bI < b.length) answer[nI++] = b[bI++];
	  return answer;
	}
}
