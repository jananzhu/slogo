package commands;

import java.util.Map;
import java.util.Queue;
import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Sum extends Command {

    private static int numParams = 2;

    public Sum(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap) {
        super(cmdQueue, model, numParams, variableMap);
    }

    @Override
    protected ISyntaxNode[] defineParams(int numParams){
        ISyntaxNode[] returnParams;
        ISyntaxNode possibleList = myParser.buildParseTree(myCmds);
        if(possibleList.hasMultipleValues()){
            numParams = 1;
            returnParams = new ISyntaxNode[numParams];
            returnParams[0] = possibleList;
        }else{
            returnParams = new ISyntaxNode[numParams];
            returnParams[0] = possibleList;
            returnParams[1] = myParser.buildParseTree(myCmds);
        }
        return returnParams;
    }

    @Override
    public double getValue() {
        if(!myParams[0].hasMultipleValues()){
            return myParams[0].getValue() + myParams[1].getValue();
        }else{
            int sum = 0;
            while(myParams[0].hasMultipleValues()){
                sum+=myParams[0].getValue();
            }
            return sum;
        }
    }

}
