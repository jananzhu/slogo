package commands;

import java.util.Queue;
import slogo_back.Model;

public class YCoordinate extends Command {


    public YCoordinate(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue () {
        return myModel.toFront("yCor", null);
    }

}
