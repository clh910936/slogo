package Parsing;

import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;
import Turtle.TurtleModel;
import Variables.VariablesModel;

import java.util.*;
import java.util.regex.Pattern;


public class CommandParser {
    public static final String WHITESPACE = "\\s+";
    public static final String COMMENT_SYMBOL = "Comment";
    public static final String VARIABLE_SYMBOL = "Variable";
    public static final String CONSTANT_SYMBOL = "Constant";
    public static final String LIST_START_SYMBOL = "ListStart";
    public static final String LIST_END_SYMBOL = "ListEnd";
    public static final String COMMAND_SYMBOL = "Command";
    public static final String MAKE_VARIABLE = "MakeVariable";
    private static final String SYNTAX_FILE = "languages/Syntax";
    private static final String LANGUAGES_FILE = "languages/";
    private static final String COMMANDS_PACKAGE_PATH = "Parsing.Commands.";

    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<Map.Entry<String, Pattern>> myCommandSymbols;
    private VariablesModel myVariablesModel;
    private TurtleModel myTurtleModel;


    public CommandParser(VariablesModel variablesModel, TurtleModel turtleModel) {
        mySymbols = new ArrayList<>();
        myCommandSymbols = new ArrayList<>();
        myVariablesModel = variablesModel;
        myTurtleModel = turtleModel;
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
    }

    public double parseCommand(String commandInput, String language) throws IllegalCommandException, ParamsExceedLimitException {
        String[] commandInputList = commandInput.split(WHITESPACE);
        if(commandInputList.length==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
        Stack commandStack = new Stack();
        double currentReturnValue = -1;
        int i = 0;
        while(i<commandInputList.length || !commandStack.isEmpty()) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput, mySymbols);
            if(input.equals(COMMENT_SYMBOL)) {
                i++;
                continue;
            }
            if(input.equals(VARIABLE_SYMBOL)) {
                CommandsGeneral commandObject = (CommandsGeneral) commandStack.peek();
                if(commandObject.getCommandName().equals(MAKE_VARIABLE)) {
                    commandObject.addParameterToCommand(rawInput.substring(1));
                    //commandObject.giveVariablesModel(myVariablesModel);
                }
                else {
                    rawInput = Double.toString(parseCommand(myVariablesModel.getVariable(rawInput.substring(1)),language));
                    input = Regex.getRegexSymbol(rawInput, mySymbols);
                }
            }
            if(input.equals(CONSTANT_SYMBOL)) {
                currentReturnValue = executeCommandsOnStack(commandStack, Double.parseDouble(rawInput), currentReturnValue);
                i++;
            }
            else if(input.equals(LIST_START_SYMBOL)) {
                String[] listContents = getListContents(commandInputList, i + 1);
                currentReturnValue = executeCommandsOnStack(commandStack, listContents, currentReturnValue);
                int listLength = listContents.length + 2;
                i+=listLength;
            }
            else if(input.equals(LIST_END_SYMBOL)) {
                throw new IllegalCommandException("List parameter is invalid");
            }
            else if (input.equals(COMMAND_SYMBOL)){
                pushNewCommandObject(commandStack, rawInput);
                i++;
            }
        }
        if(currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private void pushNewCommandObject(Stack commandStack, String input) {
        String regexCommandName = Regex.getRegexSymbol(input, myCommandSymbols);
        CommandsGeneral commandObject = (CommandsGeneral) ClassInstantiationTool.getObject(COMMANDS_PACKAGE_PATH, regexCommandName);
        commandObject.addParameterToCommand(myTurtleModel);
        commandStack.push(commandObject);
    }

    private double executeCommandsOnStack(Stack commandStack, Object parameter, double currentReturnValue) throws ParamsExceedLimitException {
        if(commandStack.isEmpty()) {
            throw new ParamsExceedLimitException();
        }
        CommandsGeneral commandObject;
        try{
            commandObject = addParameterToLastCommand(commandStack, parameter);
        }
        catch (EmptyStackException e) {
            throw new ParamsExceedLimitException();
        }
        while(commandObject.isCommandReadyToExecute()) {
            currentReturnValue = commandObject.executeCommand();
            commandStack.pop();
            if (commandStack.isEmpty()) break;
            commandObject = addParameterToLastCommand(commandStack, currentReturnValue);
        }
        return currentReturnValue;
    }


    private CommandsGeneral addParameterToLastCommand(Stack commandStack, Object value) {
        CommandsGeneral commandObject = (CommandsGeneral) commandStack.peek();
        try{
            commandObject.addParameterToCommand(value);
        }
        catch (ParamsExceedLimitException e){
            throw e;
        }
        return commandObject;
    }

    private String[] getListContents(String[] commandInputList, int currentIndex) throws IllegalCommandException{
        List<String> listContents = new ArrayList<>();
        while(currentIndex<commandInputList.length) {
            String rawInput = commandInputList[currentIndex];
            String input = Regex.getRegexSymbol(rawInput,mySymbols);
            if(!input.equals(LIST_END_SYMBOL)) listContents.add(rawInput);
            else {
                return listContents.toArray(new String[listContents.size()]);
            }
            currentIndex++;
        }
        throw new IllegalCommandException("Invalid List Parameter");
    }
}
