package Parsing;

import BackExternal.IllegalParametersException;
import Commands.CommandNode;
import BackExternal.IllegalCommandException;
import BackExternal.ModelManager;
import Models.TurtleModel;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christina Chen and Michael Zhang
 * This class is used to parse a string command input using a tree.
 * It can take in any string and will throw an exception if the syntax
 * is incorrect or invalid.
 * It depends on the CommandNode class and the ModelManager, including the
 * TurtleModel and VariablesModel that are used to handle turtle commands.
 * It also uses the SyntaxHandlerFactory and ParserTracker which are in the
 * same Parsing package.
 */

public class CommandParser {
    private ModelManager myModelManager;
    private double currentReturnValue;
    private boolean turtleEvaluated = false;
    private SyntaxHandlerFactory mySyntaxHandlerFactory;
    private List<Double> returnValues;
    private ParserTracker parserTracker;

    public CommandParser(ModelManager modelManager) {
        mySyntaxHandlerFactory = modelManager.getMySyntaxHandlerFactory();
        myModelManager = modelManager;
        returnValues = new ArrayList<>();
    }

    /**
     * Can be called in order to parse a string command input and will
     * result in the execution of the command(s) through updating of the necessary
     * models (Variables, Turtle, etc)
     * Will throw an IllegalCommandException if the command is invalid
     * @param command
     * @return double that is the return value of a particular command
     * @throws IllegalCommandException
     * @throws IllegalParametersException
     */
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

    /**
     * Used to get the return values for each individual command that was
     * executed in the previous full command input string.
     * It will return an empty list if no commands have been executed.
     * Used in order to get the input values for loop commands that have list parameters
     * with individual expressions that should all be stored as doubles.
     *
     * Assumes that it will be called after the command associated with the desired
     * return values is parsed
     * @return a list of doubles that represent return values for commands in order
     */
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
        for (int id : turtleModel.getCurrentActiveTurtles()) {
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