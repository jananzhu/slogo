package slogo_back;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentTokenProcessor extends SyntaxTokenProcessor {

    public CommentTokenProcessor (Pattern pattern) {
        super(pattern);
    }
    
    @Override
    public String processToken(String input, Queue<String> commandQueue){
        input = mySyntaxPattern.split(input)[1];
        Matcher spaceMatch = whitespacePattern.matcher(input);
        if(spaceMatch.find()){
            input = whitespacePattern.split(input)[1];
        }
        return input;
    }
    

}
