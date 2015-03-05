package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Repeat extends Command {
    
    private static final int numParams = 2;

    public Repeat (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    public double getValue () {
        int repeatTimes = (int) myParams[0].getValue();
        Double retValue = null;
        int currentIteration = 1;
        while(repeatTimes > 0){
            myVarMap.put("repcount", (double) currentIteration);
            retValue = myParams[1].getValue();
            repeatTimes--;
            currentIteration++;
        }
        return retValue;
    }

}
