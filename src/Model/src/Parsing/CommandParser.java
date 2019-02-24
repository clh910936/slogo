package Parsing;

import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;


public class CommandParser {
    public static final String WHITESPACE = "\\s+";
    private List<Map.Entry<String, Pattern>> mySymbols;

    public CommandParser() {
        mySymbols = new ArrayList<>();
    }

    public double parse(String commandInput, String language) throws IllegalCommandException{
        addPatterns("languages/" + language);
        addPatterns("languages/Syntax");

        Map<String,String> variableMap = new HashMap<>();

//        //TODO: get all variables
//        String[] commandInputList = commandInput.split(WHITESPACE);
//        for(String input : commandInputList) {
//            if(getSymbol(input).equals("Variable")) {
//                if(input.length()==0) {
//                    throw new IllegalCommandException("No variable defined");
//                }
//                String varName = input.substring(1);
//                if(!variables.hasVariable(varName)) {
//                    throw new IllegalCommandException("Variable " + varName + "does not exist");
//                }
//                variableMap.put(varName, variables.getValue(varName));
//            }
//        }

        return parseCommand(commandInput, variableMap);
    }

    public double parseCommand(String commandInput, Map<String,String> variableMap) throws IllegalCommandException{
        String[] commandInputList = commandInput.split(WHITESPACE);
        if(commandInputList.length==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        Stack commandStack = new Stack();
        //TODO: handle -1?
        double value = -1;
        for(int i = 0 ; i<commandInputList.length; i++) {
            String rawInput = commandInputList[i];
            String input;
            try {
                input = getSymbol(rawInput);
            }
            catch (IllegalCommandException e){
                throw e;
            }
            System.out.println(rawInput);
            System.out.println(input);

            if(input.equals("Comment")) continue;
            if(input.equals("Variable")) {
                input = variableMap.get(input);
            }
            if(input.equals("Constant")) {
                if(commandStack.isEmpty()) {
                    throw new IllegalCommandException("Too many parameters inputted");
                }
                Object currCommandObject = commandStack.peek();
                ((CommandsInfo) currCommandObject).addParameterToCommand(Double.parseDouble(rawInput));
                //TODO: take into account parameters that arent numbers?

                if(((CommandsInfo) currCommandObject).isCommandReadyToRemove()) {
                    value = ((CommandsInfo) currCommandObject).executeCommand();
                    commandStack.pop();
//                    if(commandStack.isEmpty()) {
                        if(i==commandInputList.length-1) break;
//                        else throw new IllegalCommandException("Too many parameters included");
//                    }
                    try{
                        Object newCommandObject = commandStack.peek();
                        ((CommandsInfo) newCommandObject).addParameterToCommand(value);
                    }
                    catch (ParamsExceedLimitException e){
                        //TODO: getCommandName()
                        throw new ParamsExceedLimitException("Too many parameters for the command " );
                    }
                    catch (EmptyStackException e) {
                        throw new ParamsExceedLimitException("Too many parameters for the command " );
                    }
                }
            }
            else {
                CommandsInfo commandObject = getCommandObject(input);
                commandStack.push(commandObject);
            }
        }
        return value;
    }

    private CommandsInfo getCommandObject(String command) {
        Class commandClass;
        Object commandObject = null;
        try{
            commandClass = Class.forName("Parsing.OneParamCommands."+command);
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
        if(commandObject!=null) return (CommandsInfo) commandObject;
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
