package Commands;


import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandsGeneral {
    protected Turtle myTurtle;
    protected VariablesModel myVariablesModel;
    protected UserDefinedCommandsModel myUserDefinedCommandsModel;
    protected List<Object> myParams;
    protected String myLanguage;

    public CommandsGeneral(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        myVariablesModel = variablesModel;
        myTurtle = turtle;
        myUserDefinedCommandsModel = userDefinedCommandsModel;
        myLanguage = language;
        myParams = new ArrayList<>();
    }

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws ClassCastException;
    public void addParameterToCommand(Object val) throws IllegalParametersException {
        if( val instanceof Turtle) {
            myTurtle = (Turtle) val;
            return;
        }
        myParams.add(val);
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
