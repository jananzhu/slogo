package slogo_back;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private List<String> myValidCommands;
    private ResourceBundle myResourceBundle;
    private Pattern constantPattern;
    
    public Parser(Model currentModel){
//        myValidCommands = currentModel.getValidCommands();
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        constantPattern = Pattern.compile(myResourceBundle.getString("HeadConstant"));

    }
    
    public Double parseRunCommands(Stack<String> commands){
        
        String command = commands.pop();
        if(myValidCommands.contains(command)){
            
        }
        
        return null;
    }
    
    public List<Double> parseInput(String input){
        Matcher m =constantPattern.matcher(input);
        if(m.find()){
            input = input.split(constantPattern.toString())[1];
            System.out.println(input.split
                               (myResourceBundle.getString("LeadingWhitespace"))[1]);
            System.out.println(m.group(0));
        }
        return null;
    }
    
    private Stack<String> inputToTokens(String input){
        return null;
    }
    
    public static void main(String[] args){
        String test = "50    \n fadfasdjlf 3453 2343 gdsdf";
        Parser testParser = new Parser(null);
        testParser.parseInput(test);
    }
    
}
