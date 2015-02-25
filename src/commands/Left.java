package commands;

import java.util.Queue;
import slogo_back.Model;

public class Left extends Command {

	private final static int numParams = 2;

    public Left(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
    }

}
