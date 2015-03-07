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
import commands.UserInstruction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import slogo_front.Manager;

public class Model {

	private Manager mgr;
	private Parser myParser;
	
	/**
	 * Map of commands a user might type in to the corresponding Command objects
	 * for handling those commands. Only contains built-in commands.
	 */
	private Map<String, String> cmdMap;
	
	/**
	 * Map of user-defined commands to the corresponding instance of those commands
	 */
	private ObservableMap<String, UserInstruction> usrCmdMap;
	
	/**
	 * Map of user-defined variables to their value. The value is the next element
	 * on the stack of values, which is also the value that will be used next time
	 * anything queries the value of a specific user-defined variable
	 */
	private ObservableMap<String, Stack<Double>> varMap;
	
	
	public static void main(String[] args){
		Model model = new Model("resources/languages/English.properties");
		String cmds = "to sumU [:A :B] [sum :A :B] sumU 1 2 \n";
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
	
	/**
	 * Constructor for Model object, back end point-of-contact.
	 * 
	 * @param langFile Name of language file to support a specific language
	 */
	public Model(String langFile){
		cmdMap = createCmdMap(langFile);
		usrCmdMap = FXCollections.observableMap(new HashMap<>());
		myParser = new Parser(this, cmdMap);
		varMap = FXCollections.observableMap(new HashMap<>());
	}
	
	/**
	 * Connects this Model instance to a specific Manager so it is
	 * able to call front end methods
	 * 
	 * @param manager Method that this Model instance is bound to
	 */
	public void setManager(Manager manager){
		mgr = manager;
	}
	
	public Parser getParser(){
		return myParser;
	}
	
	/**
	 * Each command object calls this method to request a method
	 * call on the front end to satisfy a specific user command
	 * 
	 * @param cmd Name of method to call on the front end
	 * @param params Parameters for method to be called
	 * @return Double returned by method in front end
	 */
	
	public Double toFront(String cmd, double[] params){
		return mgr.toGUI(cmd, new int[] {0}, params);
	}
	
	/**
	 * Front end passes a string of raw user input for parsing 
	 * and processing in the back end using this method
	 * 
	 * @param cmds Raw user input as a string
	 * @return The appropriate return value for the user input
	 */
	public List<Double> toBack(String cmds) {
		List<ISyntaxNode> syntaxTrees = myParser.parseInput(cmds);
		List<Double> returnValues = new ArrayList<Double>();
		for(ISyntaxNode node : syntaxTrees){
		    returnValues.add(node.getValue());
		}
		return returnValues;
	}
	
	
	/**
	 * Using the supplied file name, this method creates map to allow lookup of the 
	 * name of a corresponding Command object for a given command typed by the user.
	 * 
	 * @param filename Name of file that translates commands into english
	 * @return Map of user-entered commands to names of corresponding Command objects
	 */
	
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
	
	/*
	 * The next set of methods deal with updating the user-defined variable values
	 */
	
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
		if (varMap.containsKey(key))
			varMap.get(key).pop();
	}
	
	/*
	 * The new set of methods deal with adding new user-defined commands 
	 * to the user-defined commands map
	 */
	
	public void addNewUsrCmd(String usrCmd) {
		setUsrCmd(usrCmd, null);
	}
	
	public void setUsrCmd(String usrCmd, UserInstruction cmd) {
		usrCmdMap.put(usrCmd, cmd);
	}
	
	public UserInstruction getUsrCmd(String usrCmd) {
		return usrCmdMap.get(usrCmd);
	}
	
	public boolean usrCmdExists(String usrCmd) {
		return usrCmdMap.keySet().contains(usrCmd);
	}
	
	public void remUsrCmd(String usrCmd) {
		usrCmdMap.remove(usrCmd);
	}
}
