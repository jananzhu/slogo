package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Product extends Command {
	
	private final static int numParams = 2;
	private ISyntaxNode[] myParams;

	public Product(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		return opA.getValue() * opB.getValue();
	}

}
