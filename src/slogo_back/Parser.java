package slogo_back;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Queue;
import java.util.regex.Pattern;

public class Parser {
    private List<String> myValidCommands;
    private ResourceBundle myResourceBundle;
    private Pattern whitespacePattern;
    private CommandFactory myCommandFactory;
    private TokenProcessorFactory myProcessorFactory;
    private ISyntaxNodeFactory myISyntaxNodeFactory;

    public Parser(Model currentModel, Map<String,String> dictionary){
        myValidCommands = new ArrayList<String>();
        myValidCommands.addAll(dictionary.keySet());
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        whitespacePattern = Pattern.compile(myResourceBundle.getString("LeadingWhitespace"));
        myCommandFactory = new CommandFactory(currentModel, dictionary);
        myProcessorFactory = new TokenProcessorFactory();
        myISyntaxNodeFactory = new ISyntaxNodeFactory(currentModel, myValidCommands,
                                                      myCommandFactory, this);
    }

    
    public ISyntaxNode buildParseTree(Queue<String> tokenQueue){
        String token = tokenQueue.poll();
        return myISyntaxNodeFactory.getNode(token, tokenQueue);
    }

    public List<ISyntaxNode> parseInput(String input){
        Queue<String> tokens = inputTokenizer(input);
        List<ISyntaxNode> commands = new ArrayList<ISyntaxNode>();
        while(!tokens.isEmpty()){
            if(myValidCommands.contains(tokens.peek())){
                ISyntaxNode command = buildParseTree(tokens);
                commands.add(command);
            }else{
                throw new InvalidParameterException(tokens.peek() + " is an invalid command");
            }
        }
        return commands;
    }

    public Queue<String> inputTokenizer(String input){
        System.out.println("making tokens");
        Queue<String> tokenQueue = new LinkedList<String>();
        while(!input.matches(whitespacePattern.toString())){
            SyntaxTokenProcessor processor = myProcessorFactory.getTokenProcessor(input);
            input = processor.processToken(input, tokenQueue);
        }
        return tokenQueue;
    }
    
    public void addCommand(String commandName){
        myValidCommands.add(commandName);
        myISyntaxNodeFactory.addCommands(commandName);
        
    }
    
    public void removeCommand(String commandName){
        myValidCommands.remove(commandName);
        myISyntaxNodeFactory.removeCommands(commandName);
    }
}
