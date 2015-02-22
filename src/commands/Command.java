package commands;

import java.util.Stack;

public abstract class Command {
	
	@SuppressWarnings("unused")
	private Stack<String> inputStack;
	
	public Command(Stack<String> inStk) {
		inputStack = inStk;
	}
	
	public abstract Number run();
	
}
