package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class MakeVariable extends Command {

    private static final int numParams = 2;
    private String variableName;

    public MakeVariable (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, variableMap);
        variableName = myCmds.peek();
        myParams = defineParams(numParams);
    }

    @Override
    public double getValue () {
        Double variableValue = myParams[1].getValue();
        myModel.popVar(variableName);
        myModel.setVar(variableName, variableValue);
        return variableValue;
    }
}
