package commands;

import java.util.Stack;

public class Random extends Command {
	
	private Double opA;
	private Double opB;

	public Random(Stack<String> inStk, Double opA, Double opB) {
		super(inStk, opA, opB);
	}

	@Override
	public Double run() {
		return opA + opB;
	}

}
