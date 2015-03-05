package slogo_back;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TokenProcessorFactory {

    private List<SyntaxTokenProcessor> SyntaxProcessors;
    private ResourceBundle myResourceBundle;

    public TokenProcessorFactory(){
        SyntaxProcessors = new ArrayList<SyntaxTokenProcessor>();
        myResourceBundle = ResourceBundle.getBundle("resources/languages/Syntax");
        SyntaxProcessors.add(new SyntaxTokenProcessor(Pattern.compile(myResourceBundle.getString("HeadConstant"))));
        SyntaxProcessors.add(new SyntaxTokenProcessor(Pattern.compile(myResourceBundle.getString("HeadCommand"))));
        SyntaxProcessors.add(new SyntaxTokenProcessor(Pattern.compile(myResourceBundle.getString("HeadVariable"))));
        SyntaxProcessors.add(new ListTokenProcessor(Pattern.compile(myResourceBundle.getString("ListBlock"))));
        SyntaxProcessors.add(new CommentTokenProcessor(Pattern.compile(myResourceBundle.getString("HeadComment"))));
    }

    public SyntaxTokenProcessor getTokenProcessor(String input) throws InvalidParameterException{
        for(SyntaxTokenProcessor processor : SyntaxProcessors){
            if(processor.syntaxFound(input)){
                return processor;
            }
        }
        throw new InvalidParameterException("Parsed invalid syntax at " + input);
    }
}
