package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Tangent extends Command {
	
	private final static int numParams = 1;
	
	public Tangent(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
		super(cmdQueue, model, numParams, variableMap);
	}

	@Override
	public double getValue() {
		return Math.tan(Math.toRadians(myParams[0].getValue()));
	}

}
