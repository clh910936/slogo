package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Minus extends OneParamCommand {
    public Minus(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return -1 * (double) input;
    }
}
