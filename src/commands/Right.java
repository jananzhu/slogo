package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Right extends Command {

	private final static int numParams = 1;

    public Right(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    public double getValue () {
        return myModel.toFront("right", new double[]{myParams[0].getValue()});
    }

}
