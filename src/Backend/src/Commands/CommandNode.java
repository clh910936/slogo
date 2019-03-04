package Commands;

import Models.*;


import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode {
    protected TurtleModel myTurtleModel;
    protected VariablesModel myVariablesModel;
    protected ModelManager myModelManager;
    protected UserDefinedCommandsModel myUserDefinedCommandsModel;
    protected List<Object> myParams;
    protected String myLanguage;
    protected Turtle myTurtle;

    public CommandNode(String language, ModelManager modelManager) {
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myModelManager = modelManager;
        myTurtle = (Turtle) myTurtleModel.getCurrentTurtle();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        myLanguage = language;
        myParams = new ArrayList<>();
    }

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws ClassCastException;
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}

