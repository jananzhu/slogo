package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class UserInstruction extends Command {
	
	private final static int INITIAL_NUMBER_PARAMETERS = 1;
	private UserInstruction myParentInstruction;
	private ISyntaxNode mySynTree;

	
	/*
	 * To construct new user-defined commands use this constructor since the next parameter
	 * is the list of commands for the new command to process
	 */
	public UserInstruction(Queue<String> cmdQueue, Model model,
			Map<String, Double> variableMap) {
		super(cmdQueue, model, variableMap);
	}
	
	/*
	 * First, this user-defined-command instance is added to the global map of
	 * custom command names to their respective "source" instance. Next, the syntax
	 * tree that this custom command will traverse upon each execution will be constructed.
	 * The tree is saved to be passed on to later instances of this command.
	 */
	protected void postConstructProcessing(String instName) {
		myModel.setUsrCmd(instName, this);
		myParams = super.defineParams(INITIAL_NUMBER_PARAMETERS);
		mySynTree = myParams[0];
	}
	
	/**
	 * User-defined commands need to be cloned since their code is not persistent and therefore
	 * we cannot simply call the constructor for a new instance. 
	 * 
	 * @return Cloned instance of this user-defined command
	 */
	public Command cloneCommand(Queue<String> cmdQueue) {
		return new UserInstruction(cmdQueue, myVarMap.size(), this);
	}
	
	/*
	 * All future instances of any user-defined command are copies of the original command created
	 * by MakeUserInstructions
	 */
	public UserInstruction(Queue<String> cmdQueue, int numParams, UserInstruction parent) {
		super(cmdQueue, parent.myModel, numParams, parent.myVarMap);
		myParentInstruction = parent;
	}
	
	@Override
	public double getValue() {
		mySynTree = myParentInstruction.mySynTree;
		int paramInd = 0;
		for (String var : myVarMap.keySet())
			myVarMap.put(var, myParams[paramInd++].getValue());
		setLocalVars();
		double retValue = mySynTree.getValue();
		clearLocalVars();
		return retValue;
	}

}
