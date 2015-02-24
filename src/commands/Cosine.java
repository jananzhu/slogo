package commands;

import java.util.Queue;

import slogo_back.Model;

public class Cosine extends Command {
	
	private final static int numParams = 1;
	public Cosine(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model, numParams);
	}

	@Override
	public double getValue() {
		return Math.cos(Math.toRadians(myParams[0].getValue()));
	}

}
