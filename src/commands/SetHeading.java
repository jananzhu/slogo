package commands;

import java.util.Queue;
import slogo_back.Model;

public class SetHeading extends Command {

	private final static int numParams = 1;

    public SetHeading(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        return myModel.toFront("setHeading", new double[]{myParams[0].getValue()});
    }

}
