package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Minus extends Command {
	
	private final static int numParams = 2;
	private ISyntaxNode[] myParams;

	public Minus(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		return -1 * opA.getValue();
	}

}
