package Parsing;

import BackExternal.IllegalParametersException;
import Commands.CommandsGeneral;
import BackExternal.IllegalCommandException;
import Models.ModelManager;

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
    public static final String MAKE_VARIABLE_COMMAND = "MakeVariable";
    public static final String MAKE_COMMAND = "MakeUserInstruction";
    public static final String IFELSE_COMMAND = "IfElse";
    public static final String IF_COMMAND = "If";
    public static final String REPEAT_COMMAND = "Repeat";


    private static final String LANGUAGES_FILE = "resources/languages/";
    private static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";

    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<Map.Entry<String, Pattern>> myCommandSymbols;
    private String myLanguage;
    private ModelManager myModelManager;


    public CommandParser(ModelManager modelManager) {
        mySymbols = new ArrayList<>();
        myCommandSymbols = new ArrayList<>();
        myModelManager = modelManager;
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
        while((i<commandInputList.length || !commandStack.isEmpty())) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput, mySymbols);
            if(input.equals(COMMENT_SYMBOL)) {
                i = getIndexAfterComment(i,commandInputList);
                continue;
            }
            else if(input.equals(LIST_END_SYMBOL)) {
                throw new IllegalCommandException("List parameter is invalid");
            }
            if(input.equals(VARIABLE_SYMBOL)) {
                if(CommandParameterPredicate.checkNeedsVariableParameter(commandStack)) {
                    addParameterToLastCommand(commandStack, rawInput.substring(1));
                }
                else {
                    rawInput = myModelManager.getVariablesModel().getVariable(rawInput.substring(1));
                    input = Regex.getRegexSymbol(rawInput, mySymbols);
                }
            }
            if(input.equals(CONSTANT_SYMBOL)) {
                addParameterToLastCommand(commandStack, Double.parseDouble(rawInput));
            }
            else if(input.equals(LIST_START_SYMBOL)) {
                String[] listContents = getListContents(commandInputList, i);
                addParameterToLastCommand(commandStack, listContents);
                i+=listContents.length + 1;
            }
            else if (input.equals(COMMAND_SYMBOL)){
                if(CommandParameterPredicate.checkNeedsWordParameter(commandStack)) {
                    addParameterToLastCommand(commandStack, rawInput);
                }
                else {
                    pushNewCommandObject(commandStack, rawInput);
                }
            }
            i++;
            currentReturnValue = executeCommandIfPossible(commandStack,currentReturnValue);
        }
        if(currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private int getIndexAfterComment(int index, String[] commandInputArray) {
        String input = commandInputArray[index];
        while(!isCommand(input)||index<commandInputArray.length) {
            index++;
            input = commandInputArray[index];
        }
        return index;
    }

    public boolean isCommand(String input) {
        return (isNormalCommand(input)|| isUserCommand(input));
    }

    private boolean isNormalCommand(String input) {
        try {
            Regex.getRegexSymbol(input,myCommandSymbols);
            return true;
        }
        catch (IllegalCommandException e) {
            return false;
        }
    }

    private boolean isUserCommand(String input) {
        return myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().containsKey(input);
    }


    private void pushNewCommandObject(Stack commandStack, String commandName) {
        try{
            CommandsGeneral commandObject;
            if(isNormalCommand(commandName)) {
                commandObject = checkNormalCommands(commandName);
            } else {
                commandObject=checkUserCreatedCommands(commandName);
            }
            commandStack.push(commandObject);
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }

    private CommandsGeneral checkUserCreatedCommands(String commandName) throws IllegalCommandException {
        if(myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().containsKey(commandName)) {
            return myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().get(commandName);
        }
        else {
            throw new IllegalCommandException("Command not defined");
        }
    }

    private CommandsGeneral checkNormalCommands(String commandName) throws IllegalCommandException {
        try{
            String regexCommandName = Regex.getRegexSymbol(commandName, myCommandSymbols);
            return CommandClassFinder.getObject(COMMANDS_PACKAGE_PATH, regexCommandName, myLanguage, myModelManager);
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }


    private double executeCommandIfPossible(Stack commandStack,double currentReturnValue) {
        if(commandStack.isEmpty()) {
            return currentReturnValue;
        }
        CommandsGeneral commandObject = (CommandsGeneral) commandStack.peek();
        while(commandObject.isCommandReadyToExecute()) {
            try{
                currentReturnValue = commandObject.executeCommand();
                commandStack.pop();
                if (commandStack.isEmpty()) {
                    break;
                }
                addParameterToLastCommand(commandStack, currentReturnValue);
            }
            catch (ClassCastException e){
                throw new IllegalParametersException();
            }
        }
        return currentReturnValue;
    }


    private void addParameterToLastCommand(Stack commandStack, Object value) {
        System.out.println("Adding parameter to last command: " + String.valueOf(value));
        CommandsGeneral commandObject = (CommandsGeneral) commandStack.peek();
        try{
            commandObject.addParameterToCommand(value);
        }
        catch (IllegalParametersException e){
            throw e;
        }
    }

    private String[] getListContents(String[] commandInputList, int currentIndex) throws IllegalCommandException{
        List<String> listContents = new ArrayList<>();
        int bracketCount = 1;
        currentIndex++;
        for(int i = currentIndex;i<commandInputList.length;i++) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput,mySymbols);
            if(input.equals(LIST_START_SYMBOL)) {
                bracketCount++;
            }
            else if(input.equals(LIST_END_SYMBOL)) {
                bracketCount--;
            }
            if(bracketCount==0) return listContents.toArray(new String[listContents.size()]);
            listContents.add(rawInput);
        }
        throw new IllegalCommandException("Invalid List Parameter");
    }



}
