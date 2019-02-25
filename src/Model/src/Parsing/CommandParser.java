package Parsing;

import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;
import Parsing.Commands.LoopCommand;
import Variables.VariablesModel;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;


public class CommandParser {
    private static final String WHITESPACE = "\\s+";
    private List<Map.Entry<String, Pattern>> mySymbols;

    private static final String COMMENT_SYMBOL = "Comment";
    private static final String VARIABLE_SYMBOL = "Variable";
    private static final String CONSTANT_SYMBOL = "Constant";
    private static final String LIST_START_SYMBOL = "ListStart";
    private static final String LIST_END_SYMBOL = "ListEnd";
    private VariablesModel myVariablesModel;

    public CommandParser(VariablesModel variablesModel) {
        mySymbols = new ArrayList<>();
        myVariablesModel = variablesModel;
    }

    public double parse(String commandInput, String language) throws IllegalCommandException, ParamsExceedLimitException{
        addPatterns("languages/" + language);
        addPatterns("languages/Syntax");
        Map<String,String> variableMap = new HashMap<>();
        String[] commandInputList = commandInput.split(WHITESPACE);
        if(commandInputList.length==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        return parseCommand(commandInputList, variableMap);
    }

    public double parseCommand(String[] commandInputList, Map<String,String> variableMap) throws IllegalCommandException, ParamsExceedLimitException{
        Stack commandStack = new Stack();
        //TODO: handle -1?
        double value = -1;
        for(int i = 0 ; i<commandInputList.length; i++) {
            String rawInput = commandInputList[i];
            String input = getRegexSymbol(rawInput);

            System.out.println("Index: " + i + "|| raw: " + rawInput + "|| regex: " + input);

            if(input.equals(COMMENT_SYMBOL)) continue;

            if(input.equals(VARIABLE_SYMBOL)) {
                rawInput = myVariablesModel.getVariable(input);
                input = getRegexSymbol(rawInput);
            }
            else if(input.equals(CONSTANT_SYMBOL)) {
                if(commandStack.isEmpty()) {
                    throw new ParamsExceedLimitException();
                }
                Object currCommandObject = commandStack.peek();
                ((CommandsInfo) currCommandObject).addParameterToCommand(Double.parseDouble(rawInput));
                //TODO: take into account parameters that arent numbers?

                if(((CommandsInfo) currCommandObject).isCommandReadyToExecute()) {
                    value = ((CommandsInfo) currCommandObject).executeCommand();
                    commandStack.pop();
                    if(i==commandInputList.length-1 && commandStack.isEmpty()) break;
                    addParameterToRecentCommand(commandStack, value);
                }
            }
            else if(input.equals(LIST_START_SYMBOL)) {
                i = addListParameterToRecentCommand(commandStack, commandInputList, i);
            }
            else if(input.equals(LIST_END_SYMBOL)) {
                throw new IllegalCommandException("List parameter is invalid");
            }
            else {
                CommandsGeneral commandObject = getCommandObject(input);
                commandStack.push(commandObject);

            }
        }
        return value;
    }

    private int addListParameterToRecentCommand(Stack commandStack, String[] commandInputList, int currentIndex) {
        try {
            String[] listContents = getListContents(commandInputList, currentIndex + 1);
            Object commandObject = commandStack.peek();

            ((LoopCommandsInfo) commandObject).addListParameterToCommand(listContents);

            if (((LoopCommandsInfo) commandObject).isCommandReadyToExecute()) {
                System.out.println("EXECUTING");
                ((LoopCommandsInfo) commandObject).executeLoop();
            }



            // TODO: @michael changed this from +2 to +1
            return currentIndex+listContents.length+1;
        }
        catch (IllegalCommandException e){
            throw e;
        }
    }

    private void addParameterToRecentCommand(Stack commandStack, double value) {
        try{
            Object newCommandObject = commandStack.peek();
            ((CommandsInfo) newCommandObject).addParameterToCommand(value);
        }
        catch (ParamsExceedLimitException e){
            //TODO: getCommandName()?
            throw e;
        }
        catch (EmptyStackException e) {
            throw new ParamsExceedLimitException();
        }
    }

    private String[] getListContents(String[] commandInputList, int currentIndex) throws IllegalCommandException{

        List<String> listContents = new ArrayList<>();
        while(currentIndex<commandInputList.length) {
            String rawInput = commandInputList[currentIndex];
            String input = getRegexSymbol(rawInput);
            if(!input.equals(LIST_END_SYMBOL)) listContents.add(rawInput);
            else {
                for (String s : listContents) System.out.print(s + "|");
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