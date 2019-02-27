package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Forward extends OneParamCommand {

    public Forward(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) input);
        return (double) input;
    }
}
