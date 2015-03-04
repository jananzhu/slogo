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

import javax.annotation.PostConstruct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo_front.Manager;

public class Model {

	private Manager mgr;
	private Parser myParser;
	private Map<String, String> cmdMap;
	private ObservableMap<String, Double> varMap;
	
//	public static void main(String[] args){
//		Model model = new Model("resources/languages/English.properties");
//		String cmds = "difference product sum sum 10 10 10 2 50\n";
//		List<Double> results = model.toBack(cmds);
//		for (Double value : results) {
//			System.out.println(value);
//		}
//	}
	
	
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
		myParser = new Parser(this, cmdMap);
		Map<String, Double> map = new HashMap<>();
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
	
	public ObservableMap<String, Double> getVarMap() {
		return varMap;
	}
	
	public void setVar(String key, Double value) {
		varMap.put(key, value);
	}
	
	public Double getVar(String key) {
		return varMap.get(key);
	}
	
	public Double toFront(String cmd, double[] params){
		Method toRun;
		Double returnValue = null;
		try {
			toRun = Manager.class.getMethod(cmd, double[].class);
			returnValue = (Double) toRun.invoke(mgr, params);
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
