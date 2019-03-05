package Parsing;

import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxHandler {
    public static final String COMMENT_SYMBOL = "Comment";
    public static final String VARIABLE_SYMBOL = "Variable";
    public static final String CONSTANT_SYMBOL = "Constant";
    public static final String LIST_START_SYMBOL = "ListStart";
    public static final String LIST_END_SYMBOL = "ListEnd";
    public static final String COMMAND_SYMBOL = "Command";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";
    private ModelManager myModelManager;
    private String myLanguage;
    private List<Map.Entry<String, Pattern>> myCommandSymbols;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private static final String LANGUAGES_FILE = "resources/languages/";
    private static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private int index = 0;


    public SyntaxHandler(String language, ModelManager modelManager) {
        myLanguage = language;
        myModelManager = modelManager;
        mySymbols = new ArrayList<>();
        myCommandSymbols = new ArrayList<>();
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
    }



    public CommandNode getCommandNode(String[] commandInputList, int currentListIndex, CommandNode parent) throws IllegalParametersException {
        String rawInput = commandInputList[currentListIndex];
        String regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
        index = currentListIndex;
        if(regexInput.equals(COMMENT_SYMBOL)) {
            index = getIndexAfterComment(currentListIndex,commandInputList);
        }
        else if(regexInput.equals(LIST_END_SYMBOL)) {
            throw new IllegalCommandException("List parameter is invalid");
        }
        else if(regexInput.equals(LIST_START_SYMBOL)) {
            String[] listContents = getListContents(commandInputList, currentListIndex);
            index+=listContents.length + 1;
            return new ListInput(myLanguage, myModelManager, listContents);
        }
        if(regexInput.equals(CONSTANT_SYMBOL)) {
            return new Constant(myLanguage, myModelManager, Double.parseDouble(rawInput));
        }
        else if(regexInput.equals(VARIABLE_SYMBOL)) {
            if(parent!=null && CommandTypePredicate.checkNeedsVariableParameter(parent)) {
                return new StringInput(myLanguage, myModelManager, rawInput.substring(1));
            }
            else {
                return new Variable(myLanguage, myModelManager, rawInput.substring(1));
            }
        }
        else {
            if(parent!=null && CommandTypePredicate.checkNeedsWordParameter(parent)) {
                return new StringInput(myLanguage, myModelManager, rawInput);
            }
            return getNewCommandObject(rawInput);
        }
    }

    public int getIndex() {
        return index;
    }


    private int getIndexAfterComment(int index, String[] commandInputArray) {
        String input = commandInputArray[index];
        while(!isCommand(input)||index<commandInputArray.length) {
            index++;
            input = commandInputArray[index];
        }
        return index;
    }

    private boolean isCommand(String input) {
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


    private CommandNode getNewCommandObject(String commandName) {
        try{
            if(isNormalCommand(commandName)) {
                return getNormalCommand(commandName);
            }
            else {
                return getUserCreatedCommand(commandName);
            }
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }

    private CommandNode getUserCreatedCommand(String commandName) throws IllegalCommandException {
        if(myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().containsKey(commandName)) {
            return myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().get(commandName);
        }
        else {
            throw new IllegalCommandException("Command not defined");
        }
    }


    private CommandNode getNormalCommand(String commandName) throws IllegalCommandException {
        try{
            String command = Regex.getRegexSymbol(commandName, myCommandSymbols);
            return CommandClassFinder.getObject(COMMANDS_PACKAGE_PATH, command, myLanguage, myModelManager);
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }

    private String[] getListContents(String[] commandInputList, int currentIndex) throws IllegalCommandException{
        List<String> listContents = new ArrayList<>();
        int bracketCount = 1;
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
        throw new IllegalCommandException("Invalid ListInput Parameter");
    }
}
