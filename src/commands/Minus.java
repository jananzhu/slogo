package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Minus extends Command {
	
	private final static int numParams = 1;

	public Minus(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
		super(cmdQueue, model, numParams, variableMap);
	}

	@Override
	public double getValue() {
		return -1 * myParams[0].getValue();
	}

}
