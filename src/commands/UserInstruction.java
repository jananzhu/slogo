package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.Model;

public class UserInstruction extends Command {
	
	private int numParameters;
	private String instName;

	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, String name) {
		super(cmdQueue, model, variableMap);
		instName = name;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
