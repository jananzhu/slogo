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
    
    /**
     * Takes the token and the list of tokens in queue to process into an abstract syntax tree
     * @param token
     * @param tokenQueue
     * @return
     * @throws InvalidParameterException
     */
    public ISyntaxNode getNode(String token,Queue<String> tokenQueue) throws InvalidParameterException{
        try{
            Matcher listMatch = Pattern.compile(myResourceBundle.getString("ListBlock"), 
                                                Pattern.DOTALL).matcher(token);
            ISyntaxNode node;
            if(myValidCommands.contains(token)){
                node = myCommandFactory.getCommand(token,tokenQueue);
            }else if(token.matches(myResourceBundle.getString("HeadConstant"))){
                node = new ParameterNode(Double.parseDouble(token));
            }else if(listMatch.matches()){
                node = new ListNode(myParser.parseInput(token.substring(1, token.length()-1)));
            }else if(token.matches(myResourceBundle.getString("HeadVariable"))){
                node = new VariableNode(token,myModel.getVarMap());
            }else if (token.matches(myResourceBundle.getString("UnlimitedParameterBlock"))){
                node = new QueueNode(token.substring(1, token.length()-1),myParser);
            }else{
                throw new InvalidParameterException(token + " is invalid syntax");
            }
            return node;
        } catch(InvalidParameterException e){
            throw e;
        }
    }

    public void addCommands(String command){
        myValidCommands.add(command);
    }

    public void removeCommands(String command){
        myValidCommands.remove(command);
    }

}
