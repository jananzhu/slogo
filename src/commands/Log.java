package commands;

import java.util.Stack;

public class Log extends Command {
	
	private Double opA;

	public Log(Stack<String> inStk, Double opA) {
		super(inStk, opA);
	}

	@Override
	public Double run() {
		return Math.log(opA);
	}

}
