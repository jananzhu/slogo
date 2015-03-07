package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class Stamp extends Command {


    public Stamp(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, variableMap);
    }

    @Override
    public double getValue () {
        return myModel.toFront("stamp", null);
    }

}
