import Exceptions.IllegalCommandException;

import java.util.Map;
import java.util.Stack;

public class CommandParser {
    public static final String WHITESPACE = "\\s+";


    private int parseCommand(String commandInput, Map<String,Integer> variableMap) throws IllegalCommandException{
        String[] commandInputList = commandInput.split(WHITESPACE);
        if(commandInputList.length==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        Stack commandStack = new Stack();
        int currIndex = 0;
        while(currIndex<commandInputList.length) {
            String input = commandInputList[0];
            if(isConstant(input)) {
                if(commandStack.isEmpty()) {
                    throw new IllegalCommandException("No command found for inputted numbers");
                }
                Object currCommandObject = commandStack.peek();
                currCommandObject.addParam(input);
            }
            Object commandObject = getCommandObject(input);
            commandStack.push(commandObject);


        }



    }

    private boolean isConstant(String input) {

    }

    private Object getCommandObject(String command) {
        Object commandClass;
        try{
            commandClass = Class.forName(command);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalCommandException(command + " is not a valid command");
        }
        return commandClass;
    }



}
