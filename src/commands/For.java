package commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.Model;

public class For extends Command {

    private String myVariable;
    private int myStart;
    private int myEnd;
    private int myIncrement;

    private final static int numParams = 2;

    public For (Queue<String> cmdQueue, Model model, int numParams,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
        String parameterString = cmdQueue.peek();
        String[] parameters = parameterString.split("\\s*");
        if(parameters.length != 4){
            throw new InvalidParameterException();
        }
        if(parameters[0].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))
                && parameters[1].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))
                && parameters[2].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))
            && parameters[3].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))){
                myVariable = parameters[0].substring(1, parameters[0].length());
                myStart = (int) Double.parseDouble(parameters[1]);
                myEnd = (int) Double.parseDouble(parameters[2]);
                myIncrement = (int) Double.parseDouble(parameters[3]);
                Map<String,Double> newVariableMap = new HashMap<String,Double>();
                for(String key: myVariableMap.keySet()){
                    newVariableMap.put(key,myVariableMap.get(key));
                }
                myVariableMap.put(myVariable, (double) myStart);
            }else{
                throw new InvalidParameterException();
            }


    }

    @Override
    public double getValue () {
        double returnValue = 0;
        for(int i = myStart; i<myEnd; i+=myIncrement){
            myVariableMap.put(myVariable,(double) i);
            returnValue = myParams[1].getValue();
        }
        return returnValue;
    }

}
