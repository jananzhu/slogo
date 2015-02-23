package commands;

import java.util.Stack;

public class Cos extends Command {
	
	private Double opA;

	public Cos(Stack<String> inStk, Double opA) {
		super(inStk, opA);
	}

	@Override
	public Double run() {
		return Math.cos(Math.toDegrees(opA));
	}

}
