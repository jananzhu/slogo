package commands;

import java.util.Queue;

import slogo_back.Model;

public class ArcTangent extends Command {
	
	private final static int numParams = 1;

	public ArcTangent(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model, numParams);
	}

	@Override
	public double getValue() {
		return Math.toDegrees(Math.atan(myParams[0].getValue()));
	}

}
