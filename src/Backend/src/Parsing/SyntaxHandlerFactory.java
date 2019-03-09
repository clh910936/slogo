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

    public SyntaxHandlerFactory(ModelManager modelManager) {
        myModelManager = modelManager;
        myCommandSymbols = new HashMap<>();
        mySymbols = new HashMap<>();
        Regex.addPatterns(SYNTAX_FILE, mySymbols);
    }

    public void changeLanguage(String language) {
        myCommandSymbols.clear();
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
    }

    public CommandNode getCommandNode(CommandNode parent, ParserTracker parserTracker) throws IllegalCommandException {
        this.parent = parent;
        rawInput = parserTracker.getRawInput();
        System.out.println(rawInput);

        regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
        this.index = parserTracker.getIndex();
        this.commandInputList = parserTracker.getCommandInputList();
        System.out.println(rawInput);
        try {
            System.out.println("&(*&(*&(&"+regexInput);
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
        System.out.println("*****EVALUATE LIST START HERE");
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
        System.out.println("COMMND LIST" + Arrays.toString(commandInputList));
        for(int i = currentIndex+1;i<commandInputList.length;i++) {
            String rawInput = commandInputList[i];
            String input = Regex.getRegexSymbol(rawInput,mySymbols);
            System.out.println("RAW LIST INPUT" + rawInput);
            if(input.equals(startSymbol)) {
                System.out.println("EQUALS START SYMBOL");
                bracketCount++;
            }
            else if(input.equals(endSymbol)) {
                System.out.println("EQUALS END SYMBOL");
                bracketCount--;
            }
            if(bracketCount==0) return listContents.toArray(new String[listContents.size()]);
            listContents.add(rawInput);
            System.out.println(listContents);
        }
        throw new IllegalCommandException("Invalid ListInput Parameter");
    }
}
