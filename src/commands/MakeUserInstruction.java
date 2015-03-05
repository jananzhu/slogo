package commands;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.Model;

public class MakeUserInstruction extends Command {

    private static final int numParams = 3;
    private String instName;
    private UserInstruction usrInst;
    
    public MakeUserInstruction (Queue<String> cmdQueue, Model model, int numParams,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
        //Get instruction name
        //Get list of variable names
        //Get list of Commands
    }

    @Override
    public double getValue () {
        usrInst = new UserInstruction(myCmds, myModel, myVariableMap, instName);
        if (usrInst != null) {
        	//set variable names
        	//set commands
        	return 1;
        } else {
        	return 0;
        }
    }
}
