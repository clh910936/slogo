package Commands;


import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandsGeneral {
    protected TurtleModel myTurtle;
    protected VariablesModel myVariablesModel;
    protected UserCreatedCommandsModel myUserCreatedCommandsModel;
    protected List<Object> myParams;
    protected String myLanguage;

    public CommandsGeneral(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        myVariablesModel = variablesModel;
        myTurtle = turtleModel;
        myUserCreatedCommandsModel = userCreatedCommandsModel;
        myLanguage = language;
        myParams = new ArrayList<>();
    }

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws ClassCastException;
    public void addParameterToCommand(Object val) throws IllegalParametersException {
        if( val instanceof TurtleModel) {
            myTurtle = (TurtleModel) val;
            return;
        }
        myParams.add(val);
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
