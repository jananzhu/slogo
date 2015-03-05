package slogo_back;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISyntaxNodeFactory {

    private CommandFactory myCommandFactory;
    private List<String> myValidCommands;
    private ResourceBundle myResourceBundle;
    private Model myModel;
    private Parser myParser;
    
    public ISyntaxNodeFactory(Model model, List<String> validCommands, CommandFactory factory,
                              Parser parser){
        myModel = model;
        myValidCommands = validCommands;
        myCommandFactory = factory;
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        myParser = parser;
    }
    
    public ISyntaxNode getNode(String token,Queue<String> tokenQueue){
        Matcher listMatch = Pattern.compile(myResourceBundle.getString("ListBlock"), 
                                            Pattern.DOTALL).matcher(token);
        ISyntaxNode node;
        if(myValidCommands.contains(token)){
            node = myCommandFactory.getCommand(token,tokenQueue);
        }else if(token.matches(myResourceBundle.getString("HeadConstant"))){
            node = new ParameterNode(Double.parseDouble(token));
        }else if(listMatch.matches()){
            node = new ListNode(myParser.parseInput(token.substring(1, token.length()-1)));
        }else if(token.matches(myResourceBundle.getString("HeadVariable").toString())){
            node = new VariableNode(token,myModel.getVarMap());
        }else{
            throw new InvalidParameterException(token + " is invalid syntax");
        }
        return node;
    }
}
