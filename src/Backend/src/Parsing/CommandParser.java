package Parsing;

import BackExternal.IllegalParametersException;
import Commands.CommandsGeneral;
import BackExternal.IllegalCommandException;
import Commands.UserDefinedCommand;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

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
    public static final String MAKE_COMMAND = "MakeUserInstruction";

    private static final String LANGUAGES_FILE = "resources/languages/";
    private static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";

    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<Map.Entry<String, Pattern>> myCommandSymbols;
    private VariablesModel myVariablesModel;
    private TurtleModel myTurtleModel;
    private UserCreatedCommandsModel myUserCreatedCommandsModel;
    private String myLanguage;


    public CommandParser(VariablesModel variablesModel, TurtleModel turtleModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        mySymbols = new ArrayList<>();
        myCommandSymbols = new ArrayList<>();
        myVariablesModel = variablesModel;
        myUserCreatedCommandsModel = userCreatedCommandsModel;
        myTurtleModel = turtleModel;
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
    }

    public double parseCommand(String commandInput, String language) throws IllegalCommandException, IllegalParametersException {
        if(commandInput.length()==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        myLanguage = language;
        String[] commandInputList = commandInput.split(WHITESPACE);
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
        Stack commandStack = new Stack();
        double currentReturnValue = -1;
        int i = 0;
        while(i<commandInputList.length || !commandStack.isEmpty()) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput, mySymbols);
            System.out.println(rawInput);
            if(input.equals(COMMENT_SYMBOL)) {
                i = getIndexAfterComment(i,commandInputList);
                continue;
            }
            if(input.equals(VARIABLE_SYMBOL)) {
                if(checkLastCommand(MAKE_VARIABLE, commandStack)) {
                    addParameterToLastCommand(commandStack, rawInput.substring(1));
                    i++;
                }
                else {
                    rawInput = myVariablesModel.getVariable(rawInput.substring(1));
                    input = Regex.getRegexSymbol(rawInput, mySymbols);
                }
            }
            if(input.equals(CONSTANT_SYMBOL)) {
                currentReturnValue = executeCommandsOnStack(commandStack, Double.parseDouble(rawInput), currentReturnValue);
                i++;
            }
            else if(input.equals(LIST_START_SYMBOL)) {
                String[] listContents = getListContents(commandInputList, i);
                System.out.println(Arrays.toString(listContents));
                currentReturnValue = executeCommandsOnStack(commandStack, listContents, currentReturnValue);
                i+=listContents.length + 2;
            }
            else if(input.equals(LIST_END_SYMBOL)) {
                throw new IllegalCommandException("List parameter is invalid");
            }
            else if (input.equals(COMMAND_SYMBOL)){
                if(checkLastCommand(MAKE_COMMAND, commandStack)) {
                    addParameterToLastCommand(commandStack, rawInput);
                }
                else {
                    pushNewCommandObject(commandStack, rawInput);
                }
                i++;
            }
        }
        if(currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private int getIndexAfterComment(int index, String[] commandInputArray) {
        String input = commandInputArray[index];
        while(!isCommand(input)||index<commandInputArray.length) {
            index++;
            input = commandInputArray[index++];
        }
        return index;
    }

    private boolean checkLastCommand(String commandType, Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return ((CommandsGeneral) commandStack.peek()).getCommandName().equals(commandType);
    }

    public boolean isCommand(String input) {
        try {
            return Regex.getRegexSymbol(input,mySymbols).equals(COMMAND_SYMBOL);
        }
        catch (IllegalCommandException e) {
            return false;
        }
    }

    private void pushNewCommandObject(Stack commandStack, String input) {
        try{
            String regexCommandName = Regex.getRegexSymbol(input, myCommandSymbols);
            CommandsGeneral commandObject = CommandClassFinder.getObject(COMMANDS_PACKAGE_PATH, regexCommandName, myLanguage, myVariablesModel, myTurtleModel, myUserCreatedCommandsModel);
            commandStack.push(commandObject);
        }
        catch (IllegalCommandException e) {
            for(UserDefinedCommand command : myUserCreatedCommandsModel.getUserCreatedCommands()) {
                if(command.getCommandName().equals(input)) {
                    CommandsGeneral commandObject = command;
                    commandStack.push(commandObject);
                }
            }
            throw e;
        }
    }

    private double executeCommandsOnStack(Stack commandStack, Object parameter, double currentReturnValue) throws IllegalParametersException {
        if(commandStack.isEmpty()) {
            throw new IllegalParametersException();
        }
        CommandsGeneral commandObject;
        try{
            commandObject = addParameterToLastCommand(commandStack, parameter);
        }
        catch (EmptyStackException e) {
            throw new IllegalParametersException();
        }
        while(commandObject.isCommandReadyToExecute()) {
            try{
                currentReturnValue = commandObject.executeCommand();
            }
            catch (ClassCastException e){
                throw new IllegalParametersException();
            }
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
        catch (IllegalParametersException e){
            throw e;
        }
        return commandObject;
    }

    private String[] getListContents(String[] commandInputList, int currentIndex) throws IllegalCommandException{
        List<String> listContents = new ArrayList<>();
        int bracketCount = 0;
        for(int i = currentIndex;i<commandInputList.length;i++) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput,mySymbols);
            if(input.equals(LIST_START_SYMBOL)) {
                if(i!=currentIndex) listContents.add(rawInput);
                bracketCount++;
            }
            else if(!input.equals(LIST_END_SYMBOL)) {
                listContents.add(rawInput);
            }
            else {
                bracketCount--;
                if(bracketCount==0) return listContents.toArray(new String[listContents.size()]);
                else listContents.add(rawInput);
            }
        }
        throw new IllegalCommandException("Invalid List Parameter");
    }
}
