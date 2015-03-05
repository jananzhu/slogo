package commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;

import slogo_back.Model;

public class MakeUserInstruction extends Command {

    private String newInstName;
    private String newVarList;
    private String newCmdList;
    private Map<String, Double> newVarMap;
    private Queue<String> newCmdQueue;
    private UserInstruction newUsrInst;
    
    public MakeUserInstruction (Queue<String> cmdQueue, Model model, Map<String,Double> variableMap) {
        super(cmdQueue, model, variableMap);
        newInstName = cmdQueue.poll();
        myModel.addNewUsrCmd(newInstName);
        newVarList = cmdQueue.poll();
        //Check if actually a list of variables
        //Remove brackets
        newVarMap = new HashMap<>();
        for (String var:myParser.inputTokenizer(newVarList)) {
        	newVarMap.put(var, 0.0);
        }
        setLocalVars(newVarMap);
        newCmdList = cmdQueue.poll();
        //Check if actually a list of commands
        //DON'T remove brackets
        newCmdQueue = new LinkedList<>();
        newCmdQueue.add(newCmdList);
        try {
        	newUsrInst = new UserInstruction(newCmdQueue, myModel, newVarMap, newInstName);
        } catch (Exception e){
        	//TODO: Add handlers
        	
        } finally {
        	clearLocalVars(newVarMap);
        }
    } 

    @Override
    public double getValue () {  
        if (newUsrInst != null) {
        	myModel.setUsrCmd(newInstName, newUsrInst);
        	return 1;
        } else {
        	myModel.remUsrCmd(newInstName);
        	return 0;
        }
    }
}
