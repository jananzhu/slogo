package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo_back.Model;

public class SetPosition extends Command {

	private final static int numParams = 2;

    public SetPosition(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        List<Double> parameterList = new ArrayList<Double>();
        parameterList.add(myParams[0].getValue());
        parameterList.add(myParams[1].getValue());
        return myModel.toFront("setXY", parameterList);
    }

}
