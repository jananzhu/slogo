package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Pi extends Command {
	
	public Pi(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
		super(cmdQueue, model, variableMap);
	}
	@Override
	public double getValue() {
		return Math.PI;
	}

}
