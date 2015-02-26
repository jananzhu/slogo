package commands;

import java.util.Queue;
import slogo_back.Model;

public class PenDown extends Command {


    public PenDown(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue (){
        myModel.toFront("penDown", null);
        return 1;
    }

}
