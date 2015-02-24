package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Sin extends Command {
	
	private final static int numParams = 2;
	private ISyntaxNode[] myParams;
	
	public Sin(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		return Math.sin(Math.toDegrees(opA.getValue()));
	}

}
