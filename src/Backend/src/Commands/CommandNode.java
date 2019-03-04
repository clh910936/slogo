package Commands;

import Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommandNode {

    protected String myLanguage;
    protected TurtleModel myTurtleModel;
    protected VariablesModel myVariablesModel;
    protected ModelManager myModelManager;
    protected UserDefinedCommandsModel myUserDefinedCommandsModel;
    protected List<Object> myParams;
    protected List<CommandNode> myChildren;
    protected Turtle myTurtle;

    public CommandNode(String language, ModelManager modelManager) {
        myModelManager = modelManager;
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        myLanguage = language;
        myParams = new ArrayList<>();
        myChildren = new ArrayList<>();
        myTurtle = (Turtle) myTurtleModel.getCurrentTurtle();
    }

    public void addChild(CommandNode node) {
        myChildren.add(node);
    }

    public List<CommandNode> getChildren() {
        return Collections.unmodifiableList(myChildren);
    }

    public List<Object> getMyParams() {
        return Collections.unmodifiableList(myParams);
    }

    public void addParam(Object p) {
        myParams.add(p);
    }

    public void clearMyParams() {
        myParams.clear();
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }

    public abstract boolean isCommandReadyToExecute();

    public abstract double executeCommand();
}