package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class IsShowing extends Command {

	private final static String PARAM_NAME = "isShowing";

    public IsShowing(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, variableMap);
    }

    @Override
    public double getValue () {
    	return myModel.toFront(PARAM_NAME, null);
    }

}
