package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Random extends Command {
	
	private final static int numParams = 2;
	private ISyntaxNode[] myParams;

	public Random(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		myParams = super.defineParams(numParams);
	}

	@Override
	public double getValue() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt((int)opA.getValue());
	}

}
