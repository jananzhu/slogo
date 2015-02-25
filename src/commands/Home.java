package commands;

import java.util.Queue;

import slogo_back.Model;

public class Home extends Command {

	private static final String PARAM_NAME = "home";
	
    public Home(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model);
    }

    @Override
    public double getValue () {
    	return myModel.toFront(PARAM_NAME, null);
    }

}
