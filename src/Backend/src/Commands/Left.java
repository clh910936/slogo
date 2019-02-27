package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Left extends OneParamCommand {
    public Left(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.turnCounterClockwise((double) input);
        return (double) input;
    }
}
