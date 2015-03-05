package commands;

import java.util.ArrayList;
import java.util.List;
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
	protected List<String> myNewVarNames;
	
	public Command(Queue<String> cmdQueue, Model model, Map<String, Double> variableMap) {
		myParser = model.getParser();
		myCmds = cmdQueue;
		myModel = model;
		myVarMap = variableMap;
	}
	
	public Command(Queue<String> cmdQueue, Model model, int numParams, Map<String, Double> variableMap) {
		this(cmdQueue, model, variableMap);
		myNewVarNames = defineVariableNames();
		myParams = defineParams(numParams);
	}
	
	
	
	protected ISyntaxNode[] defineParams(int numParams){
		ISyntaxNode[] returnParams = new ISyntaxNode[numParams];
		for (int i = 0; i < numParams; i++){
		    System.out.println("numParams is" + numParams);
		    System.out.println("Calling buildParseTree from define " + i + "th parameter");
			returnParams[i] = myParser.buildParseTree(myCmds);
		}
		return returnParams;
	}
	
	/**
	 * Used by control statement commands that make new variables
	 *  to retain access to variable names after building the parse tree.
	 * @return
	 */
	protected List<String> defineVariableNames(){
	    return new ArrayList<String>();
	}
	
	protected void setLocalVars(Map<String, Double> varMap) {
		for (String var:varMap.keySet())
    		    myModel.setVar(var, varMap.get(var));
	}
	
	protected void clearLocalVars(Map<String, Double> varMap) {
		for (String var:varMap.keySet())
    		    myModel.popVar(var);
	}
}
