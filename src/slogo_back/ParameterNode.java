package slogo_back;

public class ParameterNode implements ISyntaxNode {
    private double myValue;
    
    public ParameterNode(double value){
        myValue = value;
    }
    @Override
    public double getValue () {
        return myValue;
    }
    @Override
    public boolean hasMultipleValues () {
        return false;
    }

}
