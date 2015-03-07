package slogo_back;

import java.util.Queue;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxTokenProcessor {

    protected Pattern mySyntaxPattern;
    protected Pattern whitespacePattern;
    protected ResourceBundle myResourceBundle;
    protected Matcher myCurrentMatcher;

    public SyntaxTokenProcessor(Pattern pattern){
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        mySyntaxPattern = pattern;
        whitespacePattern = Pattern.compile(myResourceBundle.getString("LeadingWhitespace"));
    }
    
    /*
     * Returns true if matches syntax pattern
     */
    public boolean syntaxFound(String input){
        myCurrentMatcher = mySyntaxPattern.matcher(input);
        return myCurrentMatcher.find();
    }
    
    /*
     * Extracts token from beginning of input string and adds it to the Queue of tokens
     */
    public String processToken(String input, Queue<String> commandQueue){
        return headTokenToQueue(mySyntaxPattern,myCurrentMatcher,commandQueue,input);
    }
    
    protected String headTokenToQueue(Pattern pattern, Matcher matcher,Queue<String> Queue,
                                    String input){
        String newInput;
        if(pattern.split(input).length <= 1){
            newInput = "";
        }else{
            newInput = pattern.split(input)[1];
            if(!newInput.matches(whitespacePattern.toString())){
                newInput = whitespacePattern.split(newInput)[1];
            }
        }
        Queue.add(matcher.group(0));
        return newInput;
    }
}
