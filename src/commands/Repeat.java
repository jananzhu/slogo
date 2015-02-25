package commands;

import java.util.Queue;
import slogo_back.Model;

public class Repeat extends Command {
    
    private static final int numParams = 2;

    public Repeat (Queue<String> cmdQueue, Model model, int numParams) {
        super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        int repeatTimes = (int) myParams[0].getValue();
        Double retValue = null;
        while(repeatTimes >= 0){
            retValue = myParams[1].getValue();
            repeatTimes --;
        }
        return retValue;
    }

}
