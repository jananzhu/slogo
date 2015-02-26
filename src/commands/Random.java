package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Random extends Command {
	
	private final static int numParams = 1;

	public Random(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
		super(cmdQueue, model, numParams, variableMap);
	}

	@Override
	public double getValue() {
		java.util.Random rand = new java.util.Random();
		return rand.nextInt((int)myParams[0].getValue());
	}

}
