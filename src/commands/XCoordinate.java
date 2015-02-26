package commands;

import java.util.Queue;
import slogo_back.Model;

public class XCoordinate extends Command {


    public XCoordinate(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue () {
        return myModel.toFront("xCor", null);
    }

}
