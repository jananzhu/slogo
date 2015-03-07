package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class UserInstruction extends Command {
	
	private final static int INITIAL_NUMBER_PARAMETERS = 1;
	private UserInstruction myParentInstruction;
	private ISyntaxNode mySynTree;

	
	//Methods for New Instance
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap) {
		super(cmdQueue, model, variableMap);
	}
	
	protected void postConstructProcessing(String instName) {
		myModel.setUsrCmd(instName, this);
		myParams = super.defineParams(INITIAL_NUMBER_PARAMETERS);
		mySynTree = myParams[0];
		//System.out.println("Updated command map");
	}
	
	//define local variables before creating new instance
	public Command cloneCommand(Queue<String> cmdQueue) {
		return new UserInstruction(cmdQueue, myVarMap.size(), this);
	}
	
	public UserInstruction(Queue<String> cmdQueue, int numParams, UserInstruction parent) {
		super(cmdQueue, parent.myModel, numParams, parent.myVarMap);
		myParentInstruction = parent;
	}
	
	//Methods for running instances
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap, int numParams, ISyntaxNode synTree) {
		super(cmdQueue, model, numParams, variableMap);
		mySynTree = synTree;
	}
	
	@Override
	public double getValue() {
		mySynTree = myParentInstruction.mySynTree;
		int paramInd = 0;
		for (String var : myVarMap.keySet())
			myVarMap.put(var, myParams[paramInd++].getValue());
		
		System.out.println("Setting variables...");
		for (String var : myVarMap.keySet())
		    System.out.println(var + " : " + myVarMap.get(var));
		System.out.println("Done variables\n\n");
		setLocalVars(myVarMap);
		double retValue = mySynTree.getValue();
		clearLocalVars(myVarMap);
		return retValue;
	}

}
