package commands;

import java.util.Queue;
import slogo_back.Model;

public class ShowTurtle extends Command {


    public ShowTurtle(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue () {
        myModel.toFront("showTurtle", null);
        return 1;
    }

}
