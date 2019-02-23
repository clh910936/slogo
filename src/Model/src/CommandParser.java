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
                    throw new IllegalCommandException("Too many parameters inputted");
                }
                Object currCommandObject = commandStack.peek();
                currCommandObject.addParameterToCommand(input);
                //TODO: take into account parameters that arent numbers

                if(currCommandObject.isCommandReadyToRemove()) {
                    int value = currCommandObject.execute();
                    commandStack.pop();
                    Object newCommandObject = commandStack.peek();
                    newCommandObject.addParameterToCommand(value);
                }
            }
            else {
                Object commandObject = getCommandObject(input);
                commandStack.push(commandObject);
            }



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
