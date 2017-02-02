import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Runner class
 */
public class ShuntingAlgorithm_Salom {
	/**
	 * Main method
	 * @param String[] args
	 * @return void
	 */
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the calculator. Please enter an expression to evaluate, or enter \"GTFO\" to quit.");
		while(true) {
			//Prompt the user
			System.out.print("calculator> ");
			String inp = scan.nextLine();

			//Exit if requested
			if(inp.toUpperCase().equals("GTFO")) {
				System.out.println("Goodbye.");
				System.exit(0);
			}

			//Correct input spacing
			String operators = "+-*/^)(";
			inp = inp.replaceAll(" ", "");
			String[] split = inp.split("");
			inp = "";
			for(int i = 0; i < split.length - 1; i++) {
				String curr = split[i];
				String next = split[i+1];
				if(operators.indexOf(next) > -1 || operators.indexOf(curr) > -1) {
					inp += curr + " ";
				}
				else {
					inp += curr;
				}
			}
			inp += split[split.length - 1];

			//Evaluate input and print the output
			System.out.println(" = " + calculator.calculate(inp));
		}
	}
}

/**
 * Calculator class. Used to evaluate infix expressions
 */
class Calculator {
	private Stack<Integer>stack;
	private Stack<Double>rpnstack;
	final String OPERATORS = "-+/*^";

	/**
	 * Evaluates an infix expression
	 * @param String input
	 * @return Double
	 */
	public Double calculate(String input) {
		//Convert to RPN
		stack = new Stack<Integer>();
		String output = "";

		for(String inp : input.split(" ")) {
			if(inp.isEmpty()) continue;
			char c = inp.charAt(0);
			int operatorIndex = OPERATORS.indexOf(c);

			if(operatorIndex != -1) {
				if(stack.isEmpty()) stack.push(operatorIndex);
				else {
					while(!stack.isEmpty()) {
						int precedence1 = operatorIndex/2;
						int precedence2 = stack.peek()/2;
						if(precedence2 > precedence1 || (precedence1 == precedence2 && c != '^')) output += OPERATORS.charAt(stack.pop()) + " ";
						else break;
					}
					stack.push(operatorIndex);
				}
			}
			else if(c == '(') stack.push(-2);
			else if(c == ')') {
				while(stack.peek() != -2) output += OPERATORS.charAt(stack.pop()) + " ";
				stack.pop();
			}
			else output += inp + " ";
		}
		while(!stack.isEmpty()) output += OPERATORS.charAt(stack.pop()) + " ";

		//Evaluate the RPN expression
		String rpn = output;
		rpnstack = new Stack<Double>();
		for(String inp : rpn.split(" ")) {
			Double inpNum = null;
			try{
				inpNum = Double.parseDouble(inp);
			}
			catch(NumberFormatException e) {}
			if(inpNum != null) {
				rpnstack.push(Double.parseDouble(inpNum + ""));
			}
			else {
				double s;
				double f;
				switch(inp) {
				case "*":
					s = rpnstack.pop();
					f = rpnstack.pop();
					rpnstack.push(f * s);
					break;
				case "/":
					s = rpnstack.pop();
					f = rpnstack.pop();
					rpnstack.push(f / s);
					break;
				case "-":
					s = rpnstack.pop();
					f = rpnstack.pop();
					rpnstack.push(f - s);
					break;
				case "+":
					s = rpnstack.pop();
					f = rpnstack.pop();
					rpnstack.push(f + s);
					break;
				case "^":
					s = rpnstack.pop();
					f = rpnstack.pop();
					rpnstack.push(Math.pow(f, s));
					break;
				default:
					System.out.println("Crap...");
					break;
				}
			}
		}
		return rpnstack.pop();
	}
}
