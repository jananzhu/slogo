package commands;

import java.util.Queue;
import slogo_back.Model;

public class SetPosition extends Command {

	private final static int numParams = 2;

    public SetPosition(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        return myModel.toFront("setXY", new double[]{myParams[0].getValue(), myParams[1].getValue()});
    }

}
