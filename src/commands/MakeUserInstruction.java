package commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import slogo_back.Model;

public class MakeUserInstruction extends Command {

    private String newInstName;
    private String newVarList;
    private String newCmdList;
    private Map<String, Double> newVarMap;
    private Queue<String> newCmdQueue;
    private UserInstruction newUsrInst;
    
    /**
     * Creates a new UserInstruction object that handles the logic for
     * the user-defined command. First make the parser aware of the new
     * command, then populate local variable map with the defined variable
     * names but placeholder values. Finally create a new UserInstruction
     * instance and pass it the block of commands to run
     */
    public MakeUserInstruction (Queue<String> cmdQueue, Model model, Map<String,Double> variableMap) {
        super(cmdQueue, model, variableMap);
        newInstName = cmdQueue.poll();
        myModel.getParser().addCommand(newInstName);
        newVarList = cmdQueue.poll();
        //TODO: Check if actually a list of variables
        newVarList = newVarList.substring(1, newVarList.length()-1);
        newVarMap = new HashMap<>();
        for (String var:myParser.inputTokenizer(newVarList)) {
        	newVarMap.put(var, 0.0);
        }
        newCmdList = cmdQueue.poll();
        //TODO: Check if actually a list of commands
        newCmdQueue = new LinkedList<>();
        newCmdQueue.add(newCmdList);
        newUsrInst = new UserInstruction(newCmdQueue, myModel, newVarMap);
        newUsrInst.postConstructProcessing(newInstName);
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
