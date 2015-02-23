package commands;

import java.util.Stack;

public class Product extends Command {
	
	private Double opA;
	private Double opB;

	public Product(Stack<String> inStk, Double opA, Double opB) {
		super(inStk, opA, opB);
	}

	@Override
	public Double run() {
		return opA * opB;
	}

}
