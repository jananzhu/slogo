package slogo_back;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Queue;
import commands.*;

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
        } else if(myModel.usrCmdExists(userInput)){
            return myModel.getUsrCmd(userInput).cloneCommand(tokens);
        }else{
            throw new InvalidParameterException(userInput + " is not a command");
        }
        commandName = "commands." + commandName;
        Class<?> commandClass = null;
        try {
            commandClass = Class.forName(commandName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor<?> constructor = commandClass.getConstructors()[0];
        Command command = null;
        try {
            command = (Command) constructor.newInstance(tokens,myModel, myModel.getVarMap());
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return command;
    }
}
