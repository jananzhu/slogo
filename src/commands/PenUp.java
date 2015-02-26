package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class PenUp extends Command {


    public PenUp(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
            super(cmdQueue, model, variableMap);
    }

    @Override
    public double getValue (){
        myModel.toFront("penUp", null);
        return 0;
    }

}
