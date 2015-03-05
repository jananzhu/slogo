package slogo_back;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private List<String> myValidCommands;
    private ResourceBundle myResourceBundle;
    private Pattern constantPattern;
    private Pattern commandPattern;
    private Pattern commentPattern;
    private Pattern listPattern;
    private Pattern variablePattern;
    private Pattern whitespacePattern;
    private CommandFactory myCommandFactory;
    private Model myModel;

    public Parser(Model currentModel, Map<String,String> dictionary){
        myValidCommands = new ArrayList<String>();
        myValidCommands.addAll(dictionary.keySet());
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        constantPattern = Pattern.compile(myResourceBundle.getString("HeadConstant"));
        commandPattern = Pattern.compile(myResourceBundle.getString("HeadCommand"));
        commentPattern = Pattern.compile(myResourceBundle.getString("HeadComment"));
        listPattern = Pattern.compile
                (myResourceBundle.getString("ListBlock"), Pattern.DOTALL);
        variablePattern = Pattern.compile(myResourceBundle.getString("HeadVariable"));
        whitespacePattern = Pattern.compile(myResourceBundle.getString("LeadingWhitespace"));
        myCommandFactory = new CommandFactory(currentModel, dictionary);
        myModel = currentModel;

    }

    public ISyntaxNode buildParseTree(Queue<String> tokenQueue){
        String token = tokenQueue.poll();
        Matcher listMatch = listPattern.matcher(token);
        ISyntaxNode node;
        if(myValidCommands.contains(token)){
            node = myCommandFactory.getCommand(token,tokenQueue);
        }else if(token.matches(constantPattern.toString())){
            node = new ParameterNode(Double.parseDouble(token));
        }else if(listMatch.matches()){
            node = new ListNode(parseInput(token.substring(1, token.length()-1)));
        }else if(token.matches(variablePattern.toString())){
            node = new VariableNode(token,myModel.getVarMap());
        }else{
            throw new InvalidParameterException(token + " is invalid syntax");
        }
        return node;
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
            Matcher constantMatch =constantPattern.matcher(input);
            Matcher listMatch = listPattern.matcher(input);
            Matcher commentMatch = commentPattern.matcher(input);
            Matcher commandMatch = commandPattern.matcher(input);
            Matcher variableMatch = variablePattern.matcher(input);
            if(constantMatch.find()){
                input = headTokenToQueue(constantPattern, constantMatch, tokenQueue, input);
            } else if(listMatch.find()){
                input = processListToken(listPattern, listMatch, tokenQueue, input);
            }else if(commandMatch.find()){
                input = headTokenToQueue(commandPattern, commandMatch, tokenQueue,input);
            }else if(commentMatch.find()){
                //skip over any comments
                input = commentPattern.split(input)[1];
                Matcher spaceMatch = whitespacePattern.matcher(input);
                if(spaceMatch.find()){
                    input = whitespacePattern.split(input)[1];
                }
            }else if(variableMatch.find()){
                input = headTokenToQueue(variablePattern, variableMatch,tokenQueue,input);
            }else{
                throw new InvalidParameterException("Parsed invalid syntax at " + input); 
            }
        }
        return tokenQueue;
    }

    private String headTokenToQueue(Pattern pattern, Matcher matcher,Queue<String> Queue,
                                    String input){
        String newInput;
        if(pattern.split(input).length <= 1){
            newInput = "";
        }else{
            newInput = pattern.split(input)[1];
            if(!newInput.matches(whitespacePattern.toString())){
                try{
                    newInput = whitespacePattern.split(newInput)[1];
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Offending input is: " + newInput);
                }
            }
        }
        Queue.add(matcher.group(0));
        return newInput;
    }

    private String processListToken(Pattern pattern, Matcher matcher, Queue<String> queue, 
                                    String input){
        int index = 0;
        int braceCount = 0;
        while(index < input.length()){
            char currentChar = input.charAt(index);
            if(currentChar == '['){
                braceCount++;
            }
            if(currentChar == ']'){
                braceCount--;
            }
            if(braceCount == 0){
                break;
            }
            index++;
        }
        if(braceCount != 0){
            throw new InvalidParameterException("Error with bracket closure at:" + input);
        }
        queue.add(input.substring(0, index+1));
        String newString;
        if(index>= input.length()-1){
            newString = "";
        }else{
            newString = input.substring(index+1,input.length()-1);
        }
        newString = newString.trim();
        return newString;
    }

}
