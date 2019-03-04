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
        Regex.addPatterns(LANGUAGES_FILE + language, myCommandSymbols);
        currentReturnValue = -1;
        currentListIndex = 0;
        while(currentListIndex<commandInputList.length) {
            //TODO: check if the first one is a comment
            CommandNode commandHead = getNewCommandObject(commandInputList[currentListIndex]);
            currentListIndex++;
            parseTree(commandHead);
        }
        return currentReturnValue;
    }


    private void parseTree(CommandNode command) throws IllegalParametersException {
        while(!command.isCommandReadyToExecute()) {
            if(currentListIndex>=commandInputList.length) {
                throw new IllegalParametersException();
            }
            String rawInput = commandInputList[currentListIndex];
            String regexInput = Regex.getRegexSymbol(rawInput, mySymbols);
            if(regexInput.equals(COMMENT_SYMBOL)) {
                currentListIndex = getIndexAfterComment(currentListIndex,commandInputList);
                continue;
            }
            else if(regexInput.equals(LIST_END_SYMBOL)) {
                throw new IllegalCommandException("List parameter is invalid");
            }
            CommandNode child = getChildNode(rawInput, regexInput, command);
            command.addChild(child);
            currentListIndex++;
        }
        currentReturnValue = evaluate(command);
    }

    private double evaluate(CommandNode command) {
        if(CommandTypePredicate.isTurtleCommand(command)) {
            turtleEvaluate(command);
        }
        for (CommandNode child : command.getChildren()) {
            command.addParam(evaluate(child));
        }
        command.clearMyParams();
        return (double) command.executeCommand();
    }

    private double turtleEvaluate(CommandNode command) {
        double currentValue = 0.0;
        TurtleModel turtleModel = myModelManager.getTurtleModel();
        VariablesModel currVariablesModel = new VariablesModel(myModelManager.getVariablesModel());
        for (int id : turtleModel.getCurrentActiveTurtles()) {
            turtleModel.setCurrentTurtle(id);
            currentValue = evaluate(command);
            myModelManager.setVariablesModel(new VariablesModel(currVariablesModel));
        }
        return currentValue;
    }

    private CommandNode getChildNode(String rawInput, String regexInput, CommandNode currCommand) throws IllegalParametersException {
        if (regexInput.equals(COMMAND_SYMBOL)) {
            if(CommandTypePredicate.checkNeedsWordParameter(currCommand)) {
                return new StringInput(myLanguage, myModelManager, rawInput);
            }
            regexInput = Regex.getRegexSymbol(rawInput, myCommandSymbols);
            return getNewCommandObject(regexInput);
        }
        else if (isUserCommand(rawInput)) {
            return getNewCommandObject(regexInput);
        }
        else if(regexInput.equals(CONSTANT_SYMBOL)) {
            return new Constant(myLanguage, myModelManager, Double.parseDouble(rawInput));
        }
        else if(regexInput.equals(LIST_START_SYMBOL)) {
            String[] listContents = getListContents(commandInputList, currentListIndex);
            currentListIndex+=listContents.length + 1;
            return new ListInput(myLanguage, myModelManager, listContents);
        }
        else if(regexInput.equals(VARIABLE_SYMBOL)) {
            if(CommandTypePredicate.checkNeedsVariableParameter(currCommand)) {
                return new StringInput(myLanguage, myModelManager, rawInput.substring(1));
            }
            else {
                return new Variable(myLanguage, myModelManager, rawInput.substring(1));
            }
        }
        throw new IllegalParametersException();
    }

//    private double parseStack(Stack commandStack, VariablesModel vm) {
//        while((currentListIndex<commandInputList.length || !commandStack.isEmpty())) {
//            String rawInput = commandInputList[currentListIndex];
//            String input = Regex.getRegexSymbol(rawInput, mySymbols);
//            if(input.equals(COMMENT_SYMBOL)) {
//                currentListIndex = getIndexAfterComment(currentListIndex,commandInputList);
//                continue;
//            }
//            else if(input.equals(LIST_END_SYMBOL)) {
//                throw new IllegalCommandException("List parameter is invalid");
//            }
//            if(input.equals(VARIABLE_SYMBOL)) {
//                if(CommandTypePredicate.checkNeedsVariableParameter(commandStack)) {
//                    addParameterToLastCommand(commandStack, rawInput.substring(1));
//                }
//                else {
//                    rawInput = vm.getVariable(rawInput.substring(1));
//                    input = Regex.getRegexSymbol(rawInput, mySymbols);
//                }
//            }
//            if(input.equals(CONSTANT_SYMBOL)) {
//                addParameterToLastCommand(commandStack, Double.parseDouble(rawInput));
//            }
//            else if(input.equals(LIST_START_SYMBOL)) {
//                String[] listContents = getListContents(commandInputList, currentListIndex);
//                addParameterToLastCommand(commandStack, listContents);
//                currentListIndex+=listContents.length + 1;
//            }
//            else if (input.equals(COMMAND_SYMBOL)){
//                if(CommandTypePredicate.checkNeedsWordParameter(commandStack)) {
//                    addParameterToLastCommand(commandStack, rawInput);
//                }
//                else {
//                    pushNewCommandObject(commandStack, rawInput);
//                }
//            }
//            currentListIndex++;
//            myModelManager.getTurtleModel().setCurrentTurtle(currentTurtle);
//            currentReturnValue = executeCommandIfPossible(commandStack,currentReturnValue);
//        }
//        if(currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
//        return currentReturnValue;
//    }

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


//    private void pushNewCommandObject(Stack commandStack, String commandName) {
//        try{
//            if(isNormalCommand(commandName)) {
//
////                if(CommandTypePredicate.isTurtleCommand(Regex.getRegexSymbol(commandName, myCommandSymbols))&&!branched) {
////                    Map<Integer,Stack> turtleStacks = new HashMap<>();
////                    Map<Integer, VariablesModel> turtleVM = new HashMap<>();
////                    branched = true;
////                    int i = currentListIndex;
////                    for(int id : myModelManager.getTurtleModel().getCurrentActiveTurtles()) {
////                        myModelManager.getTurtleModel().setCurrentTurtle(id);
////                        Stack turtleStack = new Stack();
////                        turtleStack.push(checkNormalCommands(commandName));
////                        turtleStacks.put(id, turtleStack);
////                        VariablesModel vm = myModelManager.getVariablesModel();
////                        turtleVM.put(id,vm);
////                        currentReturnValue = executeTurtleStacks(turtleStacks, turtleVM);
////                        currentListIndex = i;
////                    }
////                    myModelManager.setVariablesModel(turtleVM.get(turtleVM.size()-1));
////                    branched = false;
////                }
//                commandStack.push(checkNormalCommands(commandName));
//            }
//            else {
//                commandStack.push(checkUserCreatedCommands(commandName));
//            }
//        }
//        catch (IllegalCommandException e) {
//            throw e;
//        }
//    }

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
            return CommandClassFinder.getObject(COMMANDS_PACKAGE_PATH, commandName, myLanguage, myModelManager);
        }
        catch (IllegalCommandException e) {
            throw e;
        }
    }


//    private double executeTurtleStacks(Map<Integer,Stack> turtleStacks, Map<Integer,VariablesModel> turtleVM) {
//        double currentReturnValue = -1;
//        Stack currentTurtleStack = turtleStacks.get(currentTurtle);
//        myModelManager.getTurtleModel().setCurrentTurtle(currentTurtle);
//        currentReturnValue = executeCommandIfPossible(currentTurtleStack, currentReturnValue);
//
//        if(!currentTurtleStack.isEmpty()) {
//            currentReturnValue = parseStack(currentTurtleStack,turtleVM.get(currentTurtle));
//        }
//        return currentReturnValue;
//    }


//    private double executeCommandIfPossible(Stack commandStack,double currentReturnValue) {
//        if(commandStack.isEmpty()) {
//            return currentReturnValue;
//        }
//        CommandNode commandObject = (CommandNode) commandStack.peek();
//        while(commandObject.isCommandReadyToExecute()) {
//            try{
//                System.out.println("Current turtle: " + currentTurtle);
//                currentReturnValue = commandObject.executeCommand();
//                commandStack.pop();
//                if (commandStack.isEmpty()) {
//                    break;
//                }
//                addParameterToLastCommand(commandStack, currentReturnValue);
//                commandObject = (CommandNode) commandStack.peek();
//            }
//            catch (ClassCastException e){
//                throw new IllegalParametersException();
//            }
//        }
//        return currentReturnValue;
//    }


//    private void addParameterToLastCommand(Stack commandStack, Object value) {
//        try{
//            CommandNode commandObject = (CommandNode) commandStack.peek();
//            commandObject.addParameterToCommand(value);
//        }
//        catch (IllegalParametersException e){
//            throw e;
//        }
//        catch (EmptyStackException e){
//            throw new IllegalParametersException();
//        }
//    }


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
        throw new IllegalCommandException("Invalid ListInput Parameter");
    }



}