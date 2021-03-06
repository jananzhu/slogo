package slogo_back;

import java.security.InvalidParameterException;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTokenProcessor extends SyntaxTokenProcessor {

    private char myListOpening;
    private char myListClosing;
    
    public ListTokenProcessor (Pattern pattern, char listOpening, char listClosing) {
        super(pattern);
        myListOpening = listOpening;
        myListClosing = listClosing;
    }

    @Override
    protected String headTokenToQueue(Pattern pattern, Matcher matcher, Queue<String> queue, 
                                    String input){
        int index = 0;
        int braceCount = 0;
        while(index < input.length()){
            char currentChar = input.charAt(index);
            if(currentChar == myListOpening){
                braceCount++;
            }
            if(currentChar == myListClosing){
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
