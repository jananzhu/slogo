package commands;

import java.util.Stack;

public class Atan extends Command {
	
	private Double opA;

	public Atan(Stack<String> inStk, Double opA) {
		super(inStk, opA);
	}

	@Override
	public Double run() {
		return Math.atan(Math.toDegrees(opA));
	}

}
