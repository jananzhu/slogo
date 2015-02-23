package commands;

import java.util.Queue;

import slogo_back.ISyntaxNode;
import slogo_back.Model;
import slogo_back.Parser;

public abstract class Command implements ISyntaxNode{
	private Parser myParser;
	private Queue<String> myCmds;
	
	public Command(Queue<String> cmdQueue, Model model) {
		myParser = model.getParser();
		myCmds = cmdQueue;
	}
	
	protected ISyntaxNode[] defineParams(ISyntaxNode[] params){
		ISyntaxNode[] definedParams = new ISyntaxNode[params.length];
		for (ISyntaxNode node : params){
			node = myParser.buildParseTree(myCmds);
		}
		return definedParams;
	}
}
