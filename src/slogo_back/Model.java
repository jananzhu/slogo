package slogo_back;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import slogo_front.Manager;

public class Model {

	private Manager mgr;
	private Parser myParser;
	private Map<String, String> cmdMap;
	
	public static void main(String[] args){
		Model model = new Model(null,"resources/languages/English.properties");
		String cmds = "tan 45 ";
		List<Double> results = model.toBack(cmds);
		for (Double value : results) {
			System.out.println(value);
		}
	}
	
	public Model(Manager mananger, String langFile){
		cmdMap = createCmdMap(langFile);
		myParser = new Parser(this, cmdMap);
		
	}
	
	public void setManager(Manager manager){
		mgr = manager;
	}
	
	public Map<String, String> createCmdMap(String filename) {
		cmdMap = new HashMap<>();
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return null;
			}
	 
			prop.load(input);
	 
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				for (String userCmd : value.split("\\|")) {
					cmdMap.put(userCmd, key);
					//System.out.println(userCmd + " : " + key);
				}
			}
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return cmdMap;
	}
	
	public Double toFront(String cmd, List<Object> param){
	    Method toRun;
	    Double returnValue = null;
		try {
			toRun = Manager.class.getMethod(cmd, Double.class);
			returnValue = (Double) toRun.invoke(mgr, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public List<Double> toBack(String cmds) {
		List<ISyntaxNode> syntaxTrees = myParser.parseInput(cmds);
		List<Double> returnValues = new ArrayList<Double>();
		for(ISyntaxNode node : syntaxTrees){
		    returnValues.add(node.getValue());
		}
		return returnValues;
	}
	
	public Parser getParser(){
		return myParser;
	}
}
