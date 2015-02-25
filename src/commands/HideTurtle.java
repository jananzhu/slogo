package commands;

import java.util.Queue;
import slogo_back.Model;

public class HideTurtle extends Command {

	private final static int numParams = 2;

    public HideTurtle(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
    }

}
