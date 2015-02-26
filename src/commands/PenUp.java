package commands;

import java.util.Queue;
import slogo_back.Model;

public class PenUp extends Command {


    public PenUp(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue (){
        myModel.toFront("penUp", null);
        return 0;
    }

}
