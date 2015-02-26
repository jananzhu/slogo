package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class PenDown extends Command {


    public PenDown(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, variableMap);
    }

    @Override
    public double getValue (){
        myModel.toFront("penDown", null);
        return 1;
    }

}
