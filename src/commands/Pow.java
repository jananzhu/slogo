package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Pow extends Command {
	
	private ISyntaxNode opA;
	private ISyntaxNode opB;

	public Pow(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		ISyntaxNode[] myParams = new ISyntaxNode[]{opA, opB};
		super.defineParams(myParams);	}

	@Override
	public double getValue() {
		return Math.pow(opA.getValue(), opB.getValue());
	}

}
