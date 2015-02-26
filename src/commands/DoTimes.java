package commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.Model;

public class DoTimes extends Command {
    
    private String myVariable;
    private int myLimit;

    private final static int numParams = 2;
    
    public DoTimes (Queue<String> cmdQueue,
                    Model model,
                    int numParams,
                    Map<String, Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
        String parameterString = cmdQueue.peek();
        String[] parameters = parameterString.split("\\s*");
        if(parameters.length != 2){
            throw new InvalidParameterException();
        }
        if(parameters[0].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))
                && parameters[1].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))){
                myVariable = parameters[0].substring(1, parameters[0].length());
                myLimit = (int) Double.parseDouble(parameters[1]);
                Map<String,Double> newVariableMap = new HashMap<String,Double>();
                for(String key: myVariableMap.keySet()){
                    newVariableMap.put(key,myVariableMap.get(key));
                }
                myVariableMap.put(myVariable, (double) 1);
            }else{
                throw new InvalidParameterException();
            }
    }

    @Override
    public double getValue () {
        double returnValue = 0;
        for(int i = 1; i<myLimit; i++){
            myVariableMap.put(myVariable,(double) i);
            returnValue = myParams[1].getValue();
        }
        return returnValue;        
    }

}
