package commands;
import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class GreaterThan extends Command {

    private final static int numParams = 2;

    public GreaterThan(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap){
        super(cmdQueue,model,numParams, variableMap);
    }

    @Override
    public double getValue () {
        if(myParams[0].getValue() > myParams[0].getValue()){
            return 1;
        } else{
            return 0;
        }
    }

}
