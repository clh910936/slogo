package Parsing;

import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Commands.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

public class SyntaxHandlerFactory {
    public static final String COMMENT_SYMBOL = "Comment";
    public static final String LIST_START_SYMBOL = "ListStart";
    public static final String LIST_END_SYMBOL = "ListEnd";
    public static final String GROUP_START_SYMBOL = "GroupStart";
    public static final String GROUP_END_SYMBOL = "GroupEnd";
    public static final String WHITE_SPACE = "Whitespace";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";
    private ModelManager myModelManager;
    private String myLanguage;
    private Map<String, Pattern> myCommandSymbols;
    private Map<String, Pattern> mySymbols;
    private static final String LANGUAGES_FILE = "resources/languages/";
    private static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private int index;
    private String[] commandInputList;
    private String rawInput;
    private String regexInput;
    private CommandNode parent;

    public SyntaxHandlerFactory(String language, ModelManager modelManager, String command) {
        myLanguage = language;
        myModelManager = modelManager;
        mySymbols = new HashMap<>();
        myCommandSymbols = new HashMap<>();
        index = 0;
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
        commandInputList = command.split(mySymbols.get(WHITE_SPACE).toString());
        for(int i = 0 ;i<commandInputList.length;i++) {
            String line = commandInputList[i];
            if(Regex.match(line, mySymbols.get(COMMENT_SYMBOL))) {
                commandInputList[i] = "";
            }
        }
        commandInputList = String.join(" ",commandInputList).split(mySymbols.get(WHITE_SPACE).toString());
    }

    protected CommandNode getCommandNode(CommandNode parent) throws IllegalCommandException {
        rawInput = commandInputList[index];
        regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
        this.parent = parent;
        try {
            Method method = this.getClass().getDeclaredMethod("evaluate" + regexInput + "Symbol");
            return (CommandNode) method.invoke(this);
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
        return new ListInput(myLanguage, myModelManager, listContents);
    }

    private CommandNode evaluateGroupStartSymbol() {
        String[] listContents = getListContents(commandInputList, index, GROUP_START_SYMBOL, GROUP_END_SYMBOL);
        index+=listContents.length + 2;
        return new Group(myLanguage, myModelManager, listContents, getNewCommandObject(listContents[0]));
    }

    private CommandNode evaluateConstantSymbol() {
        index++;
        return new Constant(myLanguage, myModelManager, Double.parseDouble(rawInput));
    }
    private CommandNode evaluateVariableSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsVariableParameter(parent)) {
            return new StringInput(myLanguage, myModelManager, rawInput.substring(1));
        }
        else {
            return new Variable(myLanguage, myModelManager, rawInput.substring(1));
        }
    }

    private CommandNode evaluateCommandSymbol() {
        index++;
        if(parent!=null && CommandTypePredicate.checkNeedsWordParameter(parent)) {
            return new StringInput(myLanguage, myModelManager, rawInput);
        }
        return getNewCommandObject(rawInput);
    }



    protected boolean isDoneParsing() {
        return index==commandInputList.length;
    }

    protected CommandNode parseForCommandNode(CommandNode parent) {
        if(index>=commandInputList.length) {
            throw new IllegalParametersException();
        }
        CommandNode commandNode = getCommandNode(parent);
        return commandNode;
    }


    private boolean isCommand(String input) {
        return (isNormalCommand(input)|| isUserCommand(input));
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
            return CommandFactory.getObject(COMMANDS_PACKAGE_PATH, command, myLanguage, myModelManager);
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
