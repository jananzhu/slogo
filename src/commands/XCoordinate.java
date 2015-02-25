package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo_back.Model;

public class XCoordinate extends Command {

	private final static int numParams = 2;

    public XCoordinate(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        List<Double> parameterList = new ArrayList<Double>();
        return myModel.toFront("xCor", parameterList);
    }

}
