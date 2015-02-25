package commands;
import java.util.Queue;
import slogo_back.Model;

public class And extends Command {
    
    private final static int numParams = 2;

    public And(Queue<String> cmdQueue, Model model) {
            super(cmdQueue, model, numParams);
    }

    @Override
    public double getValue () {
        if(myParams[0].getValue() == 0){
            return 0;
        } else if(myParams[1].getValue() == 0){
            return 0;
        } else{
            return 1;
        }
    }

}
