package Commands;

import BackExternal.IllegalParametersException;
import Models.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandsGeneral {
    protected TurtleModel myTurtleModel;
    protected VariablesModel myVariablesModel;
    protected ModelManager myModelManager;
    protected UserDefinedCommandsModel myUserDefinedCommandsModel;
    protected List<Object> myParams;
    protected String myLanguage;
    protected Turtle myTurtle;

    public CommandsGeneral(String language, ModelManager modelManager) {
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myModelManager = modelManager;
        myTurtle = myTurtleModel.getCurrentTurtle();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        myLanguage = language;
        myParams = new ArrayList<>();
    }

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws ClassCastException;
    public void addParameterToCommand(Object val) throws IllegalParametersException {
        if( val instanceof Turtle) {
            myTurtleModel.addTurtleToList((Turtle) val);
            return;
        }
        myParams.add(val);
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
