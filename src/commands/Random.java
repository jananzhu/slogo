package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Random extends Command {
	
	private ISyntaxNode opA;

	public Random(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		ISyntaxNode[] myParams = new ISyntaxNode[]{opA};
		super.defineParams(myParams);	}

	@Override
	public double getValue() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt((int)opA.getValue());
	}

}
