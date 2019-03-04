package Commands;

import Models.Turtle;
import Models.ModelManager;
import Models.TurtleModel;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;
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
    protected List<CommandNode> myChildren;


    public CommandNode(String language, ModelManager modelManager) {
        myModelManager = modelManager;
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myTurtle = myTurtleModel.getCurrentTurtle();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        myLanguage = language;
        myParams = new ArrayList<>();
        myChildren = new ArrayList<>();
    }

    public abstract double evaluate() throws ClassCastException;


    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
