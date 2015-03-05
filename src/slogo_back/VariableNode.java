package slogo_back;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class VariableNode implements ISyntaxNode{
    
    private Map<String,Stack<Double>> myVariableMap;
    private String myName;
    
    public VariableNode(String name,Map<String,Stack<Double>> variableMap){
        myVariableMap = variableMap;
        myName = name;
    }

    @Override
    public double getValue() {
        if(myVariableMap.keySet().contains(myName)){
            return myVariableMap.get(myName).peek();
        }else{
            return 0;
        }
    }

}
