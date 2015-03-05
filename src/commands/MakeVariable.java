package commands;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.Model;

public class MakeVariable extends Command {

    private static final int numParams = 2;

    public MakeVariable (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    protected List<String> defineVariableNames(){
        List<String> variableNames = new ArrayList<String>();
        String nextToken = myCmds.peek();
        if(nextToken.matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))){
            variableNames.add(nextToken.substring(1, nextToken.length()));
        }else{
            throw new InvalidParameterException(nextToken + " is not in correct variable syntax");
        }
        return variableNames;
    }

    @Override
    public double getValue () {
        Double variableValue = myParams[1].getValue();
        myModel.getVarMap().put(myNewVarNames.get(0),variableValue);
        return variableValue;
    }
}
