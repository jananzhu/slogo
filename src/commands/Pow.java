package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Pow extends Command {
	
	private final static int numParams = 2;
	private ISyntaxNode[] myParams;

	public Pow(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		return Math.pow(opA.getValue(), opB.getValue());
	}

}
