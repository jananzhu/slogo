package commands;

import java.util.Queue;

import slogo_back.Model;

public class ClearScreen extends Command {

	private final static String PARAM_NAME = "clearScreen";

    public ClearScreen(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue () {
    	return myModel.toFront(PARAM_NAME, null);
    }

}
