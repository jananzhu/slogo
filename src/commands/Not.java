package commands;
import java.util.Queue;
import slogo_back.Model;

public class Not extends Command {

    private static final int numParams = 1;
    
    public Not(Queue<String> cmdQueue, Model model){
        super(cmdQueue,model,numParams);
    }
    
    @Override
    public double getValue () {
        if(myParams[0].getValue() == 0){
            return 1;
        } else{
            return 0;
        }
    }

}
