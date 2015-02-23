package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Sin extends Command {
	
	private ISyntaxNode opA;

	public Sin(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
		ISyntaxNode[] myParams = new ISyntaxNode[]{opA};
		super.defineParams(myParams);	}

	@Override
	public double getValue() {
		return Math.sin(Math.toDegrees(opA.getValue()));
	}

}
