import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StanfordWords_Salom {
	public static void main(String[] args) {
		try {
			File file = new File("sgb-words.txt");
			int[] arr = new int[5749];
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String str = scan.nextLine();
				int pos = str.hashCode() % 5749;
				pos *= pos < 0 ? -1 : 1;
				arr[pos]++;
			}
			int empty = 0;
			int max = 0;
			int countZero = 0;
			int sumZero = 0;
			for(int i = 0; i < arr.length; i++) {
				int curr = arr[i];
				empty += curr == 0 ? 0 : 1;
				max = curr > max ? curr : max;
				if(curr != 0) {
					countZero++;
					sumZero += curr;
				}
			}
			System.out.println("Empty: " + empty);
			System.out.println("Max: " + max);
			System.out.println("Average of non-zeroes: " + (((float)sumZero)/((float)countZero)));
			scan.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
