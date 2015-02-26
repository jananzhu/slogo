package commands;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.Model;

public class MakeVariable extends Command {

    private static final int numParams = 1;
    private String variableName;
    
    public MakeVariable (Queue<String> cmdQueue, Model model, int numParams,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
        String nextToken = cmdQueue.peek();
        if(nextToken.matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))){
            variableName = nextToken.substring(1, nextToken.length());
        }else{
            throw new InvalidParameterException();
        }
    }

    @Override
    public double getValue () {
        Double variableValue = myParams[1].getValue();
        myModel.getVarMap().put(variableName,variableValue);
        return variableValue;
    }
}
