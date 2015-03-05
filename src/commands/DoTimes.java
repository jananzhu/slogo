package commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class DoTimes extends Command {

    private String myVariable;
    private int myLimit;

    private final static int numParams = 2;

    public DoTimes (Queue<String> cmdQueue,
                    Model model,
                    Map<String, Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    protected ISyntaxNode[] defineParams(int numParams){
        ISyntaxNode[] returnParams = new ISyntaxNode[numParams];
        String parameterString = myCmds.poll();
        Queue<String> parameterQueue = myParser.inputTokenizer(parameterString);
        myVariable = parameterQueue.poll();
        if(myVariable.matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))){
            for(int i=0; i< numParams-1;i++){
                returnParams[i] = myParser.buildParseTree(parameterQueue);
            }
            //TODO:Throw exception for nonstandard number of parameters here, ie. >4
        } else{
            throw new InvalidParameterException(parameterString + " is invalid list of For parameters");
        }

        returnParams[numParams-1] = myParser.buildParseTree(myCmds);
        return returnParams;
    }

    @Override
    public double getValue () {
        double returnValue = 0;
        myLimit = (int) myParams[0].getValue();
        for(double i = 1; i<=myLimit; i++){
            myVarMap.put(myVariable, i);
            returnValue = myParams[1].getValue();
        }
        return returnValue;        
    }

}
