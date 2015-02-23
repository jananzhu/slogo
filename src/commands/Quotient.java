package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Quotient extends Command {
	
	private ISyntaxNode opA;
	private ISyntaxNode opB;

	public Quotient(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		ISyntaxNode[] myParams = new ISyntaxNode[]{opA, opB};
		super.defineParams(myParams);	}

	@Override
	public double getValue() {
		return opA.getValue() / opB.getValue();
	}

}
