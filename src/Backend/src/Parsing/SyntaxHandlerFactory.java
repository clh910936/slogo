package Parsing;

import BackExternal.IllegalCommandException;
import BackExternal.ModelManager;
import Commands.CommandNode;
import Commands.ListInput;
import Commands.Group;
import Commands.Constant;
import Commands.StringInput;
import Commands.Variable;
import Commands.UserDefinedCommand;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * @author christinachen
 * This class is used to handle the different syntax types that can be used in a given command input.
 * It uses the ParserTracker to set and get the current status of the command being parsed.
 * It depends on the different syntax CommandNode subclasses (ListInput, Group, Constant, etc. )
 * It also depends on the ModelManager, which is used in the constructor of CommandNodes
 */

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

    public SyntaxHandlerFactory(ModelManager modelManager) {
        myModelManager = modelManager;
        myCommandSymbols = new HashMap<>();
        mySymbols = new HashMap<>();
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
    }

    /**
     * Since the same SyntaxHandlerFactory is used for the entire duration of a program, if the language is
     * changed while it is being run, this is called each time a new command is parsed.
     * @param language
     */
    public void changeLanguage(String language) {
        myCommandSymbols.clear();
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
    }

    /**
     * Handles all of the syntax within a command input
     * Can be called by the CommandParser to fetch the next command node to put in its tree
     * @param parent
     * @param parserTracker
     * @return the CommandNode associated with the current raw input in the parserTracker
     * @throws IllegalCommandException
     */
    public CommandNode getCommandNode(CommandNode parent, ParserTracker parserTracker) throws IllegalCommandException {
        this.parent = parent;
        rawInput = parserTracker.getRawInput();
        regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
        this.index = parserTracker.getIndex();
        this.commandInputList = parserTracker.getCommandInputList();
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
        return new ListInput(myModelManager, listContents);
    }

    private CommandNode evaluateGroupStartSymbol() {
        String[] listContents = getListContents(commandInputList, index, GROUP_START_SYMBOL, GROUP_END_SYMBOL);
        index+=listContents.length + 2;
        return new Group(myModelManager, listContents, getNewCommandObject(listContents[0]));
    }

    private CommandNode evaluateConstantSymbol() {
        index++;
        return new Constant(myModelManager, Double.parseDouble(rawInput));
    }
    private CommandNode evaluateVariableSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsVariableParameter(parent)) {
            return new StringInput(myModelManager, rawInput.substring(1));
        }
        else {
            return new Variable(myModelManager, rawInput.substring(1));
        }
    }

    private CommandNode evaluateCommandSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsWordParameter(parent)) {
            return new StringInput(myModelManager, rawInput);
        }
        return getNewCommandObject(rawInput);
    }


    private boolean isNormalCommand(String input) {
        return myCommandSymbols.values().stream().anyMatch(command -> Regex.match(input,command));
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
            UserDefinedCommand commandToCopy = myModelManager.getUserDefinedCommandsModel().getUserCreatedCommands().get(commandName);
            UserDefinedCommand userDefinedCommand = new UserDefinedCommand(myModelManager, commandToCopy.getCommandName(), commandToCopy.getCommands(),commandToCopy.getMyVariables());
            return userDefinedCommand;
        }
        else {
            throw new IllegalCommandException("Command not defined");
        }
    }


    private CommandNode getNormalCommand(String commandName) throws IllegalCommandException {
        try{
            String command = Regex.getRegexSymbol(commandName, myCommandSymbols);
            return CommandFactory.getObject(COMMANDS_PACKAGE_PATH, command, myModelManager);
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
            System.out.println(listContents);
        }
        throw new IllegalCommandException("Invalid ListInput Parameter");
    }
}
