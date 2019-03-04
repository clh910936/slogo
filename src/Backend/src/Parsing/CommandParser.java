package Parsing;

import BackExternal.IllegalParametersException;
import Commands.*;
import BackExternal.IllegalCommandException;
import Models.ModelManager;
import Models.TurtleModel;
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

    private static final String LANGUAGES_FILE = "resources/languages/";
    private static final String SYNTAX_FILE = LANGUAGES_FILE + "Syntax";
    private static final String COMMANDS_PACKAGE_PATH = "Commands.";

    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<Map.Entry<String, Pattern>> myCommandSymbols;
    private String myLanguage;
    private ModelManager myModelManager;
    private String[] commandInputList;
    private int currentListIndex;
    private double currentReturnValue;
    private boolean turtleEvaluated = false;

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
        commandInputList = commandInput.split(WHITESPACE);
        myCommandSymbols.clear();
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
        currentReturnValue = -1;
        currentListIndex = 0;

        while(currentListIndex<commandInputList.length) {
            CommandNode commandHead = buildCommandTree(null);
            currentReturnValue = Double.valueOf(String.valueOf(evaluate(commandHead)));
        }
        if (currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private CommandNode parseForCommandNode(CommandNode parent) {
        if(currentListIndex>=commandInputList.length) {
            throw new IllegalParametersException();
        }
        String rawInput = commandInputList[currentListIndex];
        String input = Regex.getRegexSymbol(rawInput, mySymbols);
        currentListIndex++;
        return getCommandNode(rawInput,input,parent);
    }

    private CommandNode buildCommandTree(CommandNode parent) {
        CommandNode currentNode = parseForCommandNode(parent);
        while(currentNode!=null) {
            if(currentNode.isCommandReadyToExecute()) {
                return currentNode;
            }
            else {
                while(!currentNode.isCommandReadyToExecute()) {
                    currentNode.addChild(buildCommandTree(currentNode));
                }
            }
        }
        throw new IllegalParametersException();
    }



    private Object evaluate(CommandNode command) {
        if(CommandTypePredicate.isTurtleCommand(command)&&!turtleEvaluated) {
            return turtleEvaluate(command);
        }
        else {
            for(CommandNode child : command.getChildren()) {
                command.addParam(evaluate(child));
            }
            Object returnValue = command.executeCommand();
            command.clearMyParams();
            return returnValue;
        }
    }

    private Object turtleEvaluate(CommandNode command) {
        Object currentValue = null;
        turtleEvaluated = true;
        TurtleModel turtleModel = myModelManager.getTurtleModel();
        VariablesModel currVariablesModel = new VariablesModel(myModelManager.getVariablesModel());
        for (int id : turtleModel.getCurrentActiveTurtles()) {
            turtleModel.setCurrentTurtle(id);
            currentValue = evaluate(command);
            myModelManager.setVariablesModel(new VariablesModel(currVariablesModel));
        }
        turtleEvaluated = false;
        return currentValue;
    }

    private CommandNode getCommandNode(String rawInput, String regexInput, CommandNode parent) throws IllegalParametersException {

        if(regexInput.equals(COMMENT_SYMBOL)) {
            currentListIndex = getIndexAfterComment(currentListIndex,commandInputList);
        }
        else if(regexInput.equals(LIST_END_SYMBOL)) {
            throw new IllegalCommandException("List parameter is invalid");
        }
        else if(regexInput.equals(LIST_START_SYMBOL)) {
            String[] listContents = getListContents(commandInputList, currentListIndex);
            currentListIndex+=listContents.length + 1;
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