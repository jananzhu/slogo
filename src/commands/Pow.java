package commands;

import java.util.Stack;

public class Pow extends Command {
	
	private Double opA;
	private Double opB;

	public Pow(Stack<String> inStk, Double opA, Double opB) {
		super(inStk, opA, opB);
	}

	@Override
	public Double run() {
		return Math.pow(opA, opB);
	}

}
