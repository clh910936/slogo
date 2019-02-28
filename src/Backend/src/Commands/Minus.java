package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Minus extends OneParamCommand {
    public Minus(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return -1 * (double) input;
    }
}
