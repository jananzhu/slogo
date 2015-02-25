package commands;

import java.util.Queue;
import slogo_back.Model;

public class Forward extends Command {

	private final static int numParams = 2;

    public Forward(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
    }

}
