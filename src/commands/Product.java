package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Product extends Command {
	
	private final static int numParams = 2;

	public Product(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
		super(cmdQueue, model, numParams, variableMap);
	}

	@Override
	public double getValue() {
		return myParams[0].getValue() * myParams[1].getValue();
	}

}
