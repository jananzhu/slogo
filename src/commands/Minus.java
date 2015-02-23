package commands;

import java.util.Stack;

public class Minus extends Command {
	
	private Double opA;

	public Minus(Stack<String> inStk, Double opA) {
		super(inStk, opA);
	}

	@Override
	public Double run() {
		return -1 * opA;
	}

}
