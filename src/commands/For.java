package commands;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class For extends Command {

    private String myVariable;

    //TODO: Note that numParams is currently set to 1 instead of 2 because first "param" is parsed through 
    // defineVariableNames. Not sure if this is a good permanent solution. B
    private final static int numParams = 4;

    public For (Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    //    @Override
    //    protected List<String> defineVariableNames(){
    //        List<String> variableNames = new ArrayList<String>();
    //
    //        String parameterString = myCmds.poll();
    //        parameterString = parameterString.substring(1, parameterString.length()-1);
    //        String[] parameters = parameterString.split("\\s+");
    //        if(parameters.length != 4){
    //            for(String s: parameters){
    //                System.out.println("Parameter is" + s);
    //            }
    //            throw new InvalidParameterException(parameterString + " does not contain 4 parameters");
    //        }
    //        if(parameters[0].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))
    //                && parameters[1].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))
    //                && parameters[2].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))
    //                && parameters[3].matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadConstant"))){
    //            variableNames.add(parameters[0].substring(1, parameters[0].length()));
    //            myStart = (int) Double.parseDouble(parameters[1]);
    //            myEnd = (int) Double.parseDouble(parameters[2]);
    //            myIncrement = (int) Double.parseDouble(parameters[3]);
    //            return variableNames;
    //        }else{
    //            throw new InvalidParameterException(parameterString + " is in invalid format");
    //        }
    //    }

    @Override
    protected ISyntaxNode[] defineParams(int numParams){
        ISyntaxNode[] returnParams = new ISyntaxNode[numParams];
        String parameterString = myCmds.poll();
        Queue<String> parameterQueue = myParser.inputTokenizer(parameterString);
        myVariable = parameterQueue.poll();
        if(myVariable.matches(ResourceBundle.getBundle("resources/languages/Syntax").getString("HeadVariable"))){
            for(int i=0; i< numParams-1;i++){
                returnParams[i] = myParser.buildParseTree(parameterQueue, myVariableMap);
            }
            //TODO:Throw exception for nonstandard number of parameters here, ie. >4
        } else{
            throw new InvalidParameterException(parameterString + " is invalid list of For parameters");
        }

        returnParams[numParams-1] = myParser.buildParseTree(myCmds, myVariableMap);
        return returnParams;
    }

    @Override
    public double getValue () {
        double returnValue = 0;
        int myStart = (int) myParams[0].getValue();
        int myEnd = (int) myParams[1].getValue();
        int myIncrement = (int) myParams[2].getValue();
        for(int i = myStart; i<myEnd; i+=myIncrement){
            myVariableMap.put(myVariable,(double) i);
            returnValue = myParams[3].getValue();
        }
        return returnValue;
    }

}
