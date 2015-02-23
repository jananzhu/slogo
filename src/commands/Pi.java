package commands;

import java.util.Stack;

public class Pi extends Command {
	
	public Pi(Stack<String> inStk) {
		super(inStk);
	}

	@Override
	public Double run() {
		return Math.PI;
	}

}
