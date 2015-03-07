package commands;

import java.util.Map;
import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;
import slogo_back.Parser;

public abstract class Command implements ISyntaxNode{
	protected Parser myParser;
	protected Queue<String> myCmds;
	protected ISyntaxNode[] myParams;
	protected Model myModel;
	protected Map<String,Double> myVarMap;
	
	/**
	 * Command constructor for commands that do not take any parameters or commands that require additional
	 * processing using the command queue before constructing the syntax tree (which would pop off all the
	 * parameters).
	 * 
	 * @param cmdQueue Queue of commands/variables/numbers/etc that have yet been processed
	 * @param model Model instance that this command reports back to
	 * @param variableMap Map of locally user-defined variables
	 */
	public Command(Queue<String> cmdQueue, Model model, Map<String, Double> variableMap){
		myParser = model.getParser();
		myCmds = cmdQueue;
		myModel = model;
		myVarMap = variableMap;
	}
	
	/**
	 * Constructor for commands with parameters where the syntax tree can be created during construction
	 * 
	 * @param numParams Number of parameters that this command takes
	 */
	public Command(Queue<String> cmdQueue, Model model, int numParams, Map<String, Double> variableMap){
		this(cmdQueue, model, variableMap);
		myParams = defineParams(numParams);
	}

	/**
	 * Recursively calls Parser (recursive because parser calls more Command objects until
	 * it encounters a number), once for each parameter that the command takes. This 
	 * uses recursion to produce a syntax tree, which allows us to check whether or not
	 * the user formatted their command correctly.
	 * 
	 * @param numParams Number of parameters that a command takes
	 * @return Root(s) of syntax tree(s) that was/were constructed
	 */
	protected ISyntaxNode[] defineParams(int numParams){
		ISyntaxNode[] returnParams = new ISyntaxNode[numParams];
		for (int i = 0; i < numParams; i++){
			returnParams[i] = myParser.buildParseTree(myCmds);
		}
		return returnParams;
	}
	
	/**
	 * Takes local map of user-defined variables and their values and updates the global
	 * variable map in Model to include the values in their respective variable value stacks
	 */
	protected void setLocalVars() {
		for (String var:myVarMap.keySet())
    		    myModel.setVar(var, myVarMap.get(var));
	}
	
	/**
	 * Pops the top of the stack of each variable used locally in the command
	 */
	protected void clearLocalVars() {
		for (String var:myVarMap.keySet())
    		    myModel.popVar(var);
	}
	
	public boolean hasMultipleValues(){
	    return false;
	}
}
