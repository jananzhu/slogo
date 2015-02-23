package slogo_back;

import java.lang.reflect.Method;
import java.util.List;

import slogo_front.Manager;

public class Model {

	private Manager mgr;
	private Parser myParser;
	
	public Model(){
		
	}
	
	public void setManager(Manager manager){
		mgr = manager;
	}
	
	public Double toFront(String cmd, Double param){
		Method toRun;
		try {
			toRun = Manager.class.getMethod(cmd, Double.class);
			return (Double) toRun.invoke(mgr, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ISyntaxNode> toBack(String cmds) {
		myParser = new Parser(this);
		return myParser.parseInput(cmds);
	}
	
	public Parser getParser(){
		return myParser;
	}
}
