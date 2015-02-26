package slogo_back;

import java.util.List;
import java.util.Map;

public class VariableNode implements ISyntaxNode{
    
    private Map<String,Double> myVariableMap;
    private String myName;
    
    public VariableNode(String name,Map<String,Double> variableMap){
        myVariableMap = variableMap;
        myName = name;
    }

    @Override
    public double getValue() {
        if(myVariableMap.keySet().contains(myName)){
            return myVariableMap.get(myName);
        }else{
            return 0;
        }
    }

}
