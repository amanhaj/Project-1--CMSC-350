import java.util.Stack;
import javax.swing.JOptionPane;
/*
 * Author: Amanda Hajati
 * FileName: Project1_Infix.java
 * Course: CMSC 350 6381
 * Date Completed: 1/23/2019
 */
public class Project1_Infix {
	public static int evaluate(String input) {

		//Remove whitespace
		String inputTrimmed = input.trim();
		//Initialize the operator and operand stacks
		Stack<Integer> operand = new Stack<>();
		Stack<Character> operator = new Stack<>();

		//Tokenize the input string
		char[] tokens = inputTrimmed.toCharArray();

		//While there are more tokens...
		int index = 0;
		while (index < tokens.length) {

			//get the next token
			if ((String.valueOf(tokens[index]).matches("[0-9]+")) == true ) {
				StringBuffer combine = new StringBuffer();

				/* if it is an operand
				push it onto the operand stack */
				while (index < tokens.length && (String.valueOf(tokens[index]).matches("[0-9]+")) == true)
					combine.append(tokens[index++]);
				index--;
				operand.push((int) Integer.parseInt(combine.toString()));
			}
			/*else if it is a left parenthesis
			push it onto the operator stack */
			else if (tokens[index] == '(')
				operator.push(tokens[index]);

			//else if it is a right parenthesis
			else if (tokens[index] == ')') {
				//while top of the operator stack not a left parenthesis
				while (operator.peek() != '(')
					/*pop two operands and an operator
					perform the calculation
					push the result onto the operand stack */
					operand.push(solve(operator.pop(), operand.pop(), operand.pop()));
				operator.pop();
			}
			// else if it is an operator
			else if ((String.valueOf(tokens[index]).matches("[\\+\\-\\*\\/]")) == true) {
				/*while the operator stack is not empty and
				the operator at the top of the stack has higher
				or the same precedence than the current operator */
				while (!operator.empty() && hasPrecedence(tokens[index], operator.peek()))
					/*pop two operands and perform the calculation
					push the result onto the operand stack */
					operand.push(solve(operator.pop(), operand.pop(), operand.pop()));
				//push the current operator on the operators stack.
				operator.push(tokens[index]);
			}
			index++;
		}
		//while the operator stack is not empty
		while (!operator.empty())
			/* pop two operands and an operator
			perform the calculation
			push the result onto the operand stack
			 */
			operand.push(solve(operator.pop(), operand.pop(), operand.pop()));

		//the final result is a the top of the operand stack
		return operand.pop();
	}

	
	//determines precedence
	public static boolean hasPrecedence(char operand1, char operand2) {
		
		if (operand2 == '(' || operand2 == ')')
			return false;
			if ((operand1 == '*' || operand1 == '/') && (operand2 == '+' || operand2 == '-'))
				return false;
				else
					return true;
	    }
	
	//method called by method calculation to throw exception if dividing by 0
	public static void quotient( int numerator, int denominator )throws IllegalArgumentException {
			  
		if ( denominator == 0 ) {
			JOptionPane.showMessageDialog(null, "You cannot divide by zero! Try again.");
		    
			}	
		}
	
	// takes in the operator and two operands popped off the stack--
	//returns the solution
	public static int solve(char operator, int b, int a) {
	
		if (operator == '+') {
			return a + b;
		} else if (operator == '-') {
			return a - b;
		} else if (operator == '*') {
			return a * b;
		} else if (operator == '/') {
			if (b == 0) {
				quotient(a,b);
				
			} else {
			return a / b;
		}
	}
		return 0;
	}}