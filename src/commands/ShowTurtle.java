package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class ShowTurtle extends Command {


    public ShowTurtle(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, variableMap);
    }

    @Override
    public double getValue () {
        myModel.toFront("showTurtle", null);
        return 1;
    }

}
