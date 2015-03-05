package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class UserInstruction extends Command {
	
	private static final int initNumParams = 1;
	private ISyntaxNode mySynTree;

	
	//Methods for New Instance
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, String name) {
		super(cmdQueue, model, initNumParams, variableMap);
		mySynTree = myParams[0];
	}
	
	//define local variables before creating new instance
	public Command cloneCommand(Queue<String> cmdQueue) {
		return new UserInstruction(cmdQueue, myModel, myVarMap, myVarMap.size(), mySynTree);
	}
	
	
	//Methods for running instances
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, int numParams, ISyntaxNode synTree) {
		super(cmdQueue, model, numParams, variableMap);
		mySynTree = synTree;
		int paramInd = 0;
		for (String var : myVarMap.keySet())
			myVarMap.put(var, myParams[paramInd++].getValue());
	}
	
	@Override
	public double getValue() {
		setLocalVars(myVarMap);
		double retValue = mySynTree.getValue();
		clearLocalVars(myVarMap);
		return retValue;
	}

}
