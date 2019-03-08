package Parsing;

import BackExternal.IllegalParametersException;
import Commands.*;
import BackExternal.IllegalCommandException;
import BackExternal.ModelManager;
import Models.TurtleModel;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private ModelManager myModelManager;
    private double currentReturnValue;
    private boolean turtleEvaluated = false;
    private SyntaxHandlerFactory mySyntaxHandlerFactory;
    private List<Double> returnValues;

    public CommandParser(ModelManager modelManager) {
        myModelManager = modelManager;
        returnValues = new ArrayList<>();
    }

    public double parseCommand(String commandInput, String language) throws IllegalCommandException, IllegalParametersException {
        if(commandInput.length()==0) {
            throw new IllegalCommandException("No Command Inputted");
        }
        mySyntaxHandlerFactory = new SyntaxHandlerFactory(language, myModelManager,commandInput);
        currentReturnValue = -1;
        while(!mySyntaxHandlerFactory.isDoneParsing()) {
            CommandNode commandHead = buildCommandTree(null);
            currentReturnValue = Double.valueOf(String.valueOf(evaluate(commandHead)));
            returnValues.add(currentReturnValue);
        }
        if (currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    public List<Double> getReturnValues() {
        return returnValues;
    }

    private CommandNode buildCommandTree(CommandNode parent) {
        CommandNode currentNode = mySyntaxHandlerFactory.parseForCommandNode(parent);
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