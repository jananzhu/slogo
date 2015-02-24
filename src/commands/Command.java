package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;
import slogo_back.Parser;

public abstract class Command implements ISyntaxNode{
	private Parser myParser;
	private Queue<String> myCmds;
	protected ISyntaxNode[] myParams;
	
	public Command(Queue<String> cmdQueue, Model model) {
		myParser = model.getParser();
		myCmds = cmdQueue;
	}
	
	public Command(Queue<String> cmdQueue, Model model, int numParams) {
		this(cmdQueue, model);
		myParams = defineParams(numParams);
	}
	
	private ISyntaxNode[] defineParams(int numParams){
		ISyntaxNode[] returnParams = new ISyntaxNode[numParams];
		for (int i = 0; i < numParams; i++){
			returnParams[i] = myParser.buildParseTree(myCmds);
		}
		return returnParams;
	}
}
