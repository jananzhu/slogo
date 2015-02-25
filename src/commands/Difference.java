package commands;

import java.util.Queue;

import slogo_back.Model;

public class Difference extends Command {
	
	private final static int numParams = 2;

	public Difference(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model, numParams);
	}

	@Override
	public double getValue() {
		return myParams[0].getValue() - myParams[1].getValue();
	}

}
