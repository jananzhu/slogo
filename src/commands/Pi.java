package commands;

import java.util.Queue;

import slogo_back.Model;

public class Pi extends Command {
	
	public Pi(Queue<String> cmdQueue, Model model) {
		super(cmdQueue, model);
	}
	@Override
	public double getValue() {
		return Math.PI;
	}

}
