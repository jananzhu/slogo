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
        myModel.getParser().addCommand(newInstName);
        newVarList = cmdQueue.poll();
        //Check if actually a list of variables
        newVarList = newVarList.substring(1, newVarList.length()-1);
        newVarMap = new HashMap<>();
        for (String var:myParser.inputTokenizer(newVarList)) {
        	newVarMap.put(var, 0.0);
        }
        newCmdList = cmdQueue.poll();
        //Check if actually a list of commands
        //DON'T remove brackets
        newCmdQueue = new LinkedList<>();
        newCmdQueue.add(newCmdList);
        newUsrInst = new UserInstruction(newCmdQueue, myModel, newVarMap);
        newUsrInst.postConstructProcessing(newInstName);
        //myModel.setUsrCmd(newInstName, newUsrInst);
    } 

    @Override
    public double getValue () {  
        if (newUsrInst == null) {
            myModel.remUsrCmd(newInstName);
            myModel.getParser().removeCommand(newInstName);
            return 0;
        }
        return 1;
    }
}
