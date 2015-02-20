package slogo_back;

import java.lang.reflect.Method;

import slogo_front.Manager;

public class Model {

	private Manager mgr;
	
	public Model(){
		
	}
	
	public void setManager(Manager manager){
		mgr = manager;
	}
	
	public Number toFront(String cmd, Number param){
		Method toRun;
		try {
			toRun = Manager.class.getMethod(cmd, Number.class);
			return (Number) toRun.invoke(mgr, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Number toBack(String cmds) {
		Parser parser = new Parser();
		return parser.parse(cmds);
	}
}
