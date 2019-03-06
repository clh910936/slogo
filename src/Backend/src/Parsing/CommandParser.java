package Parsing;

import BackExternal.IllegalParametersException;
import Commands.*;
import BackExternal.IllegalCommandException;
import BackExternal.ModelManager;
import Models.TurtleModel;
import Models.VariablesModel;


public class CommandParser {
    public static final String WHITESPACE = "\\s+";

    private ModelManager myModelManager;
    private String[] commandInputList;
    private int currentListIndex;
    private double currentReturnValue;
    private boolean turtleEvaluated = false;
    private SyntaxHandler mySyntaxHandler;

    public CommandParser(ModelManager modelManager) {
        myModelManager = modelManager;
    }

    public double parseCommand(String commandInput, String language) throws IllegalCommandException, IllegalParametersException {
        if(commandInput.length()==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        mySyntaxHandler = new SyntaxHandler(language, myModelManager);
        commandInputList = commandInput.split(WHITESPACE);
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
//        currentListIndex++;
        if(currentListIndex>=commandInputList.length) {
            throw new IllegalParametersException();
        }
        CommandNode commandNode = mySyntaxHandler.getCommandNode(commandInputList,currentListIndex,parent);
        currentListIndex = mySyntaxHandler.getIndex();
        return commandNode;
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

}