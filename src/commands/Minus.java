package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Minus extends Command {
	
	private ISyntaxNode opA;

	public Minus(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		ISyntaxNode[] myParams = new ISyntaxNode[]{opA};
		super.defineParams(myParams);	}

	@Override
	public double getValue() {
		return -1 * opA.getValue();
	}

}
