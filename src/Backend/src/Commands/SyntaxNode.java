package Commands;

import Models.ModelManager;
import Models.TurtleModel;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public abstract class SyntaxNode {

    protected String myLanguage;
    protected TurtleModel myTurtleModel;
    protected VariablesModel myVariablesModel;
    protected ModelManager myModelManager;
    protected UserDefinedCommandsModel myUserDefinedCommandsModel;

    public SyntaxNode(String language, ModelManager modelManager) {
        myModelManager = modelManager;
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        myLanguage = language;
    }

    public boolean isReadyToExecute() {
        return true;
    }

    public abstract double evaluate();

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
