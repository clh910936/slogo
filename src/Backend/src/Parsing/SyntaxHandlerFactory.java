package Parsing;

import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Commands.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

public class SyntaxHandlerFactory {
    public static final String LIST_START_SYMBOL = "ListStart";
    public static final String LIST_END_SYMBOL = "ListEnd";
    public static final String GROUP_START_SYMBOL = "GroupStart";
    public static final String GROUP_END_SYMBOL = "GroupEnd";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";
    private Map<String, Pattern> myCommandSymbols;
    private Map<String, Pattern> mySymbols;
    private static final String LANGUAGES_FILE = "resources/languages/";
    public static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private ModelManager myModelManager;
    private String regexInput;
    private String rawInput;
    private int index;
    private String[] commandInputList;

    private CommandNode parent;

    public SyntaxHandlerFactory(String language, ModelManager modelManager) {
        myModelManager = modelManager;
        myCommandSymbols = new HashMap<>();
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
    }

    public void changeLanguage(String language) {
        myCommandSymbols.clear();
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
    }

    public CommandNode getCommandNode(CommandNode parent, ParserTracker parserTracker) throws IllegalCommandException {

        this.parent = parent;
        regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
        this.index = parserTracker.getIndex();
        this.rawInput = parserTracker.getRawInput();
        this.commandInputList = commandInputList;
        try {
            Method method = this.getClass().getDeclaredMethod("evaluate" + regexInput + "Symbol");
            CommandNode returnNode = (CommandNode) method.invoke(this);
            parserTracker.setIndex(index);
            return returnNode;
        }
        catch (Exception e) {
            throw new IllegalCommandException("Illegal syntax");
        }
    }

    private void evaluateListEndSymbol() {
        throw new IllegalCommandException("List parameter is invalid");
    }

    private void evaluateGroupEndSymbol() {
        throw new IllegalCommandException("Grouping parameter is invalid");
    }

    private CommandNode evaluateListStartSymbol() {
        String[] listContents = getListContents(commandInputList, index, LIST_START_SYMBOL, LIST_END_SYMBOL);
        index+=listContents.length + 2;
        return new ListInput(this, myModelManager, listContents);
    }

    private CommandNode evaluateGroupStartSymbol() {
        String[] listContents = getListContents(commandInputList, index, GROUP_START_SYMBOL, GROUP_END_SYMBOL);
        index+=listContents.length + 2;
        return new Group(this, myModelManager, listContents, getNewCommandObject(listContents[0]));
    }

    private CommandNode evaluateConstantSymbol() {
        index++;
        return new Constant(this, myModelManager, Double.parseDouble(rawInput));
    }
    private CommandNode evaluateVariableSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsVariableParameter(parent)) {
            return new StringInput(this, myModelManager, rawInput.substring(1));
        }
        else {
            return new Variable(this, myModelManager, rawInput.substring(1));
        }
    }

    private CommandNode evaluateCommandSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsWordParameter(parent)) {
            return new StringInput(this, myModelManager, rawInput);
        }
        return getNewCommandObject(rawInput);
    }




    private boolean isNormalCommand(String input) {
        return myCommandSymbols.values().stream().anyMatch(command -> Regex.match(input,command));
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
            return CommandFactory.getObject(COMMANDS_PACKAGE_PATH, command, this, myModelManager);
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }

    private String[] getListContents(String[] commandInputList, int currentIndex, String startSymbol, String endSymbol) throws IllegalCommandException{
        List<String> listContents = new ArrayList<>();
        int bracketCount = 1;
        for(int i = currentIndex+1;i<commandInputList.length;i++) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput,mySymbols);
            if(input.equals(startSymbol)) {
                bracketCount++;
            }
            else if(input.equals(endSymbol)) {
                bracketCount--;
            }
            if(bracketCount==0) return listContents.toArray(new String[listContents.size()]);

            listContents.add(rawInput);
        }
        throw new IllegalCommandException("Invalid ListInput Parameter");
    }
}
