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
    private ParserTracker parserTracker;

    public CommandParser(ModelManager modelManager, SyntaxHandlerFactory syntaxHandlerFactory) {
        mySyntaxHandlerFactory = syntaxHandlerFactory;
        myModelManager = modelManager;
        returnValues = new ArrayList<>();
    }


    public double parseCommand(String command) throws IllegalCommandException, IllegalParametersException {
        parserTracker = new ParserTracker(command);
        currentReturnValue = -1;
        while(!parserTracker.isDoneParsing()) {
            CommandNode commandHead = buildCommandTree(null);
            double returnValue = Double.valueOf(String.valueOf(evaluate(commandHead)));
            if(returnValue!=-1) currentReturnValue = returnValue;
            else currentReturnValue = 0;
            commandHead.clearChildren();
            commandHead.clearMyParams();
            returnValues.add(currentReturnValue);
        }
        if (currentReturnValue==-1) throw new IllegalCommandException("Command did not execute correctly");
        return currentReturnValue;
    }

    public List<Double> getReturnValues() {
        return returnValues;
    }

    private CommandNode buildCommandTree(CommandNode parent) {
        parserTracker.setNextInputString();
        CommandNode currentNode = mySyntaxHandlerFactory.getCommandNode(parent, parserTracker);
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
            return addParamsAndExecute(command);
        }
    }

    private Object addParamsAndExecute(CommandNode command) {
        for(CommandNode child : command.getChildren()) {
            command.addParam(evaluate(child));
        }
        Object returnValue = command.executeCommand();
        command.clearMyParams();
        return returnValue;
    }

    private Object evaluateForAllTurtles(CommandNode command) {
        Object currentValue = -1;
        TurtleModel turtleModel = myModelManager.getTurtleModel();
        turtleEvaluated = true;
        VariablesModel currVariablesModel = new VariablesModel(myModelManager.getVariablesModel());
        VariablesModel newVariablesModel = new VariablesModel(myModelManager.getVariablesModel());
        System.out.println("YUHHH" + myModelManager.getTurtleModel().getCurrentActiveTurtles());
        for (int id : turtleModel.getCurrentActiveTurtles()) {
            System.out.println("********" + command + " " + id);
            double currTurtleId = turtleModel.getCurrentTurtleIndex();
            turtleModel.setCurrentTurtle(id);
            currentValue = addParamsAndExecute(command);
            turtleModel.setCurrentTurtle((int) currTurtleId);
            newVariablesModel = new VariablesModel(myModelManager.getVariablesModel());
            myModelManager.setVariablesModel(new VariablesModel(currVariablesModel));
        }
        myModelManager.setVariablesModel(newVariablesModel);
        turtleEvaluated = false;

        return currentValue;
    }

}