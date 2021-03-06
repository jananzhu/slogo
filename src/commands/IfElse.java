package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class IfElse extends Command {

    private final static int numParams = 3;
    
    public IfElse (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    public double getValue () {
        Double expressionBoolean = myParams[0].getValue(); 
        if(expressionBoolean == 1){
            return myParams[1].getValue();
        } else{
            return myParams[2].getValue();
        }
    }

}
