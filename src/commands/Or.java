package commands;
import java.util.Queue;
import slogo_back.Model;

public class Or extends Command {

    private static final int numParams = 2;
    
    public Or(Queue<String> cmdQueue, Model model){
        super(cmdQueue,model,numParams);
    }
    @Override
    public double getValue () {
        if(myParams[0].getValue() == 0 && myParams[1].getValue() == 0){
            return 0;
        }else{
            return 1;
        }
    }

}
