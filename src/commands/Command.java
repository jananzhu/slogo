package commands;

import java.util.Stack;

public abstract class Command {
	public Command(Stack<String> inStk) {
		
	}
	
	public Command(Stack<String> inStk, Double opA) {
		
	}
	
	public Command(Stack<String> inStk, Double opA, Double opB) {
		this(inStk, opA);
	}
	
	public abstract Double run();
	
}
