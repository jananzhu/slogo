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
import java.util.Stack;



import commands.Command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo_front.Manager;

public class Model {

	private Manager mgr;
	private Parser myParser;
	private Map<String, String> cmdMap;
	private Map<String, Command> usrCmdMap;
	private ObservableMap<String, Stack<Double>> varMap;
	
	public static void main(String[] args){
		Model model = new Model("resources/languages/English.properties");
		String cmds = "";
		List<Double> results = model.toBack(cmds);
		for (Double value : results) {
			System.out.println(value);
		}
	}
	
	
	/*
	 * Code for adding listener
	 * 
	 * varMap.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                System.out.println("Detected a change! ");
            }
        });
	 */
	public Model(String langFile){
		cmdMap = createCmdMap(langFile);
		usrCmdMap = new HashMap<>();
		myParser = new Parser(this, cmdMap);
		Map<String, Stack<Double>> map = new HashMap<>();
		varMap = FXCollections.observableMap(map);
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
	
	public ObservableMap<String, Stack<Double>> getVarMap() {
		return varMap;
	}
	
	public void setVar(String key, Double value) {
		if (varMap.containsKey(key))
			varMap.get(key).add(value);
		else {
			Stack<Double> temp = new Stack<>();
			temp.push(value);
			varMap.put(key, temp);
		}
	}
	
	public Double getVar(String key) {
		return varMap.get(key).peek();
	}
	
	public void popVar(String key) {
		varMap.get(key).pop();
	}
	
	public void addNewUsrCmd(String usrCmd) {
		setUsrCmd(usrCmd, null);
	}
	
	public void setUsrCmd(String usrCmd, Command cmd) {
		usrCmdMap.put(usrCmd, cmd);
	}
	
	public boolean usrCmdExists(String usrCmd) {
		return usrCmdMap.keySet().contains(usrCmd);
	}
	
	public void remUsrCmd(String usrCmd) {
		usrCmdMap.remove(usrCmd);
	}
	
	public Double toFront(String cmd, double[] params){
		return mgr.toGUI(cmd, new int[] {0}, params);
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
