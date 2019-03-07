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
        commandInputList = commandInput.split(WHITESPACE);
        mySyntaxHandler = new SyntaxHandler(language, myModelManager,commandInputList);
        currentReturnValue = -1;
        while(!mySyntaxHandler.isDoneParsing()) {
            CommandNode commandHead = buildCommandTree(null);
            System.out.println("*****************************");
            System.out.println(commandHead);
            currentReturnValue = Double.valueOf(String.valueOf(evaluate(commandHead)));
        }
        if (currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    private CommandNode buildCommandTree(CommandNode parent) {
        CommandNode currentNode = mySyntaxHandler.parseForCommandNode(parent);
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
            return evaluateForAllTurtles(command);
        }
        else {
            for(CommandNode child : command.getChildren()) {
                command.addParam(evaluate(child));
            }
            Object returnValue = command.executeCommand();
            command.clearMyParams();
            command.clearChildren();
            return returnValue;
        }
    }

    private Object evaluateForAllTurtles(CommandNode command) {
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