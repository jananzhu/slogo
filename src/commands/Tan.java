package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Tan extends Command {
	
	private final static int numParams = 1;
	private ISyntaxNode[] myParams;
	
	public Tan(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		return Math.tan(Math.toDegrees(myParams[0].getValue()));
	}

}
