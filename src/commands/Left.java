package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Left extends Command {

	private final static int numParams = 1;
	private final static String PARAM_NAME = "left";

    public Left(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    public double getValue () {
    	return myModel.toFront(PARAM_NAME, new double[]{myParams[0].getValue()});
    }

}
