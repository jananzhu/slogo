package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class UserInstruction extends Command {
	
	private final static int INITIAL_NUMBER_PARAMETERS = 1;
	private ISyntaxNode mySynTree;

	
	//Methods for New Instance
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap) {
		super(cmdQueue, model, variableMap);
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
		for (String var : myVarMap.keySet())
		    System.out.println(var + " : " + myVarMap.get(var));
	}
	
	@Override
	public double getValue() {
		setLocalVars(myVarMap);
		double retValue = mySynTree.getValue();
		clearLocalVars(myVarMap);
		return retValue;
	}
	
	protected void postConstructProcessing(String instName) {
		myModel.setUsrCmd(instName, this);
		myParams = super.defineParams(INITIAL_NUMBER_PARAMETERS);
		mySynTree = myParams[0];
		//System.out.println("Updated command map");
	}

}
