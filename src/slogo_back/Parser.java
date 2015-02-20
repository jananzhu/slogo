package slogo_back;

import java.util.List;
import java.util.Stack;

public class Parser {
    private List<String> myValidCommands;
    
    public Parser(Model currentModel){
        myValidCommands = currentModel.getValidCommands();
    }
    
    public List<Double> parseRunCommands(Stack<String> commands){
        String command = commands.pop();
        if(myValidCommands.contains(command)){
            
        }
        
        return null;
    }
    
    
    
}
