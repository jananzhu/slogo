package slogo_back;

import java.util.List;

public class ListNode implements ISyntaxNode {
    
    private List<ISyntaxNode> myCommands;

    public ListNode(List<ISyntaxNode> commandBlock){
        myCommands = commandBlock;
    }
    
    @Override
    public double getValue () {
        Double returnValue = null;
        for(ISyntaxNode command : myCommands){
            returnValue = command.getValue();
        }
        return returnValue;
    }

    @Override
    public boolean hasMultipleValues () {
        return false;
    }

}
