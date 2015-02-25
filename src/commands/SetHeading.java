package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import slogo_back.Model;

public class SetHeading extends Command {

	private final static int numParams = 2;

    public SetHeading(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        List<Double> parameterList = new ArrayList<Double>();
        Double degrees = myParams[0].getValue();
        parameterList.add(degrees);
        myModel.toFront("setHeading", parameterList);
        return degrees;
    }

}
