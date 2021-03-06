package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.Model;

public class If extends Command{

    private static final int numParams = 2;
    
    public If (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }
    
    public double getValue(){
       Double expressionBoolean = myParams[0].getValue(); 
       if(expressionBoolean == 1){
           return myParams[1].getValue();
       } else{
           return 0;
       }
    }

}
