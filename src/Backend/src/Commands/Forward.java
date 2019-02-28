package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Forward extends OneParamCommand {

    public Forward(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) input);
        return (double) input;
    }
}
