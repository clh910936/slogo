package Parsing;

import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;
import Turtle.TurtleModel;
import Variables.VariablesModel;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;


public class CommandParser {
    private static final String WHITESPACE = "\\s+";
    private static final String COMMENT_SYMBOL = "Comment";
    private static final String VARIABLE_SYMBOL = "Variable";
    private static final String CONSTANT_SYMBOL = "Constant";
    private static final String LIST_START_SYMBOL = "ListStart";
    private static final String LIST_END_SYMBOL = "ListEnd";
    private static final String MAKE_VARIABLE = "MakeVariable";
    private static final String LANGUAGES_SYNTAX_FILE = "languages/Syntax";
    private static final String LANGUAGES_FILE = "languages/";

    private List<Map.Entry<String, Pattern>> mySymbols;
    private VariablesModel myVariablesModel;
    private TurtleModel myTurtleModel;


    public CommandParser(VariablesModel variablesModel, TurtleModel turtleModel) {
        mySymbols = new ArrayList<>();
        myVariablesModel = variablesModel;
        myTurtleModel = turtleModel;
    }

    public double parseCommand(String commandInput, String language) throws IllegalCommandException, ParamsExceedLimitException {
        String[] commandInputList = commandInput.split(WHITESPACE);
        if(commandInputList.length==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        addPatterns(LANGUAGES_FILE + language);
        addPatterns(LANGUAGES_SYNTAX_FILE);

        Stack commandStack = new Stack();
        double currentReturnValue = -1;
        int i = 0;

        while(i<commandInputList.length || !commandStack.isEmpty()) {
            String rawInput = commandInputList[i];
            String input = getRegexSymbol(rawInput);

            if(input.equals(COMMENT_SYMBOL)) {
                i++;
                continue;
            }

            if(input.equals(VARIABLE_SYMBOL)) {
                CommandsGeneral commandObject = (CommandsGeneral) commandStack.peek();
                if(commandObject.getCommandName().equals(MAKE_VARIABLE)) {
                    commandObject.addParameterToCommand(rawInput.substring(1));
                    // TODO: add giveVariablesModel method
                    //commandObject.giveVariablesModel(myVariablesModel);
                }
                else {
                    rawInput = myVariablesModel.getVariable(rawInput.substring(1));
                    input = getRegexSymbol(rawInput);
                }
            }
            if(input.equals(CONSTANT_SYMBOL)) {
                if(commandStack.isEmpty()) {
                    throw new ParamsExceedLimitException();
                }
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
            else {
                CommandsGeneral commandObject = getCommandObject(input);
                commandStack.push(commandObject);
                i++;
            }
        }
        if(currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private double executeCommandsOnStack(Stack commandStack, Object parameter, double currentReturnValue) {
        CommandsGeneral commandObject;
        try{
            commandObject = addParameterToCommand(commandStack, parameter);
        }
        catch (EmptyStackException e) {
            throw new ParamsExceedLimitException();
        }
        while(commandObject.isCommandReadyToExecute()) {
            currentReturnValue = commandObject.executeCommand();
            commandStack.pop();
            if (commandStack.isEmpty()) break;
            commandObject = addParameterToCommand(commandStack, currentReturnValue);
        }
        return currentReturnValue;
    }


    private CommandsGeneral addParameterToCommand(Stack commandStack, Object value) {
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
            String input = getRegexSymbol(rawInput);
            if(!input.equals(LIST_END_SYMBOL)) listContents.add(rawInput);
            else {
                return listContents.toArray(new String[listContents.size()]);
            }
            currentIndex++;
        }
        throw new IllegalCommandException("Invalid List Parameter");
    }

    private String getRegexSymbol(String rawInput) {
        String input;
        try {
            input = getSymbol(rawInput);
        }
        catch (IllegalCommandException e){
            throw e;
        }
        return input;
    }

    private CommandsGeneral getCommandObject(String command) {
        Class commandClass;
        Object commandObject = null;
        try{
            commandClass = Class.forName("Parsing.Commands."+command);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalCommandException(command + " is not a valid command");
        }
        try{
            commandObject = commandClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException e){
            System.out.println(commandClass + " has no constructor");
        }
        catch (InstantiationException e) {
            System.out.println("Could not instantiate " + commandClass);
        }
        catch (IllegalAccessException e) {
            System.out.println("Could not instantiate " + commandClass);
        }
        catch (InvocationTargetException e) {
            System.out.println("Could not instantiate " + commandClass);
        }
        if(commandObject!=null) return (CommandsGeneral) commandObject;
        else {
            throw new IllegalArgumentException();
        }
    }

    private void addPatterns (String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol (String text) throws IllegalCommandException{
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalCommandException(text + " is not a valid command");
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
    }
