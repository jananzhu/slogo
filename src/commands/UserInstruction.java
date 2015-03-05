package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.Model;

public class UserInstruction extends Command {
	
	private static final int initNumParams = 1;
	private Map<String, Double> varMap;
	private Queue<String> initCmdQueue;

	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, String name) {
		super(cmdQueue, model, initNumParams, variableMap);
	}
	
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, int numParams) {
		super(cmdQueue, model, numParams, variableMap);
	}
	
	
	
	//define local variables before creating new instance
	public Command cloneCommand() {
		setLocalVars(varMap);
		Command newInst = new UserInstruction(initCmdQueue, myModel, myVariableMap, myVariableMap.size());
		clearLocalVars(varMap);
		return newInst;
	}
	
	@Override
	public double getValue() {
		return myParams[0].getValue();
	}
	
	@Override
	protected void saveCmdQueue() {
		initCmdQueue = myCmds;
	}

}
