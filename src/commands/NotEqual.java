package commands;
import java.util.Queue;
import slogo_back.Model;

public class NotEqual extends Command {

    private static final int numParams = 2;

    public NotEqual(Queue<String> cmdQueue, Model model){
        super(cmdQueue, model, numParams);
    }
    
    @Override
    public double getValue () {
        if(myParams[0].getValue() != myParams[1].getValue()){
            return 1;
        } else{
            return 0;
        }
    }

}
