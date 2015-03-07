package commands;
import java.util.Map;
import java.util.Queue;
import slogo_back.ISyntaxNode;
import slogo_back.Model;

public class Or extends Command {

    private static final int numParams = 2;
    
    public Or(Queue<String> cmdQueue, Model model,Map<String,Double> variableMap){
        super(cmdQueue,model,numParams, variableMap);
    }
    
    @Override
    public double getValue() {
        boolean returnBoolean;
        if(!myParams[0].hasMultipleValues()){
            returnBoolean = !(myParams[0].getValue() == 0 & myParams[1].getValue() == 0);
        } else{
            returnBoolean = myParams[0].getValue() == 1;
            while(myParams[0].hasMultipleValues()){
                returnBoolean = (returnBoolean | (myParams[0].getValue() == 1));
            }
        }
        if(returnBoolean){
            return 1;
        }else{
            return 0;
        }
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

}
