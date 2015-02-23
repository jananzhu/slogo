package commands;

import java.util.Stack;

public class Tan extends Command {
	
	private Double opA;
	private Double opB;

	public Tan(Stack<String> inStk, Double opA, Double opB) {
		super(inStk, opA, opB);
	}

	@Override
	public Double run() {
		return opA + opB;
	}

}
