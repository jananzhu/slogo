package slogo_back;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import commands.*;

import commands.Command;

public class CommandFactory {

    private Model myModel;
    private Map<String,String> myDictionary;

    public CommandFactory(Model model, Map<String,String> dictionary){
        myModel = model;
        myDictionary = dictionary;
    }

    public Command getCommand(String userInput, Queue<String> tokens){
        String commandName = null;
        if(myDictionary.containsKey(userInput)){
            commandName = myDictionary.get(userInput);
        } else{
            throw new InvalidParameterException(userInput + " is not a command");
        }
        Class commandClass = null;
        try {
             commandClass = Class.forName(commandName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Command command = null;
        try {
             command = (Command) commandClass.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return command;
    }
}
