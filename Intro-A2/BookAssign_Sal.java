/**
 * @author Trevor Salom
 *
 * *R3.12*
 *
 * What does the following method do? Give an example of how you can call the method.
 * public class BankAccount {
 * 	public void mystery(BankAccount that, double amount) {
 *  	this.balance = this.balance - amount;
 *   	that.balance = that.balance + amount;
 *   }
 *   // Other bank account methods
 *  }
 *
 * The following method moves money from one BankAccount's balance to anothers.
 * It can be called with
 * '''
 * 		(new BankAccount()).mystery(new BankAccount(), 100);
 * '''
 *
 * *R3.15*
 *
 * Consider the following implementation of a class Square:
 * public class Square {
 * 	private int sideLength;
 * 	private int area;
 *  public Square(int initialLength) {
 *  	sideLength = initialLength;
 *   	area = sideLength * sideLength;
 *  }
 *  public int getArea() { return area; }
 *  public void grow() { sideLength = 2 * sideLength; }
 * }
 * What error does this class have? How would you fix it?
 *
 * Variables sideLength and area need to be initialized
 *
 * *R4.6 c, e*
 *
 * What are the values of the following expressions? In each line, assume that
 * double x = 2.5;
 * double y = -1.5;
 * int m = 18;
 * int n = 4;
 * c. 5 * x - n / 5
 * 	12.5
 * e. Math.sqrt(Math.sqrt(n))
 * 	1.4142135623730951
 *
 * *R4.7 d,e,f*
 *
 * What are the values of the following expressions, assuming that n is 17 and
 * m is 18?
 * d. (m + n) / 2.0
 * 	17.5
 * e. (int) (0.5 * (m + n))
 *  17
 * f. (int) Math.round(0.5 * (m + n))
 *  18
 *
 * *R4.10*
 *
 * Find three run-time errors in the following program.
 * public class HasErrors {
 * 	public static void main(String[] args) {
 *   int x = 0;
 *   int y = 0;
 *   Scanner in = new Scanner("System.in");
 *   System.out.print("Please enter an integer:");
 *   x = in.readInt();
 *   System.out.print("Please enter another integer: ");
 *   x = in.readInt();
 *   System.out.println("The sum is " + x + y);
 *  }
 * }
 *
 * In the print statement it will concatenate the values of x and y, and it is
 * setting x, not y, to the value of the second integer, and new scanner does not
 * accept a string as a constructor parameter
 *
 * *R4.11*
 *
 * Consider the following code:
 * CashRegister register = new CashRegister();
 * register.recordPurchase(19.93);
 * register.receivePayment(20, 0, 0, 0, 0);
 * System.out.print("Change: ");
 * System.out.println(register.giveChange());
 *
 * The code segment prints the total as 0.07000000000000028. Explain why. Give a
 * recommendation to improve the code so that users will not be confused.
 *
 * This occurs because an int is being subtracted from double, and the preciscion
 * differences cause the total to be printed strangely. To fix this, the output
 * should be limited to 2 decimal places using printf
 *
 * *R4.15*
 *
 * Write pseudocode for a program that reads a name (such as Harold James Morgan)
 * and then prints a monogram consisting of the initial letters of the first,
 * middle, and last name (such as HJM).
 *
 * String monogram = str.charAt(0);
 * while(str.indexof(" ") != -1)
 * 		str = substring(str.indexof(" "), str.length())
 * 	 	mongram += str.charAt(0)
 *
 * *R4.16*
 * Write pseudocode for a program that computes the first and last digit of a
 * number. For example, if the input is 23456, the program should print 2 and 6.
 * Hint: %, Math.log10.
 *
 * First digit:
 * 	n = (int)(Math.log10(Math.abs(inp)))
 *  return inp/Math.pow(10, n)
 *
 * Last digit:
 *  return inp % 10
 *
 * You are a new developer at Seoul Consultancy Group, you have been assigned the
 * pseudocode in 4.21 to implement. Rise to the rank of Senior Programmer IV if you
 * do it correctly. Be sure to write a method to implement the pseudo-code and call
 * it from main with the values specified in the text.
 *
 * See code below
 */

import java.util.Scanner;

public class BookAssign_Sal {
	public static void main (String[] args) {
		System.out.println(new SeoulString("Gateway", 2, 4));
	}
}

class SeoulString {
	String str;
	int posI;
	int posJ;

	public SeoulString(String str, int posI, int posJ) {
		this.str = str;
		this.posI = posI;
		this.posJ = posJ;
	}

	public String toString() {
		return str.substring(0, posI) + str.charAt(posJ) +
					 str.substring(posI + 1, posJ) + str.charAt(posI) + str.substring(posJ + 1, str.length());

	}

}
