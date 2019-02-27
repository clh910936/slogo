package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class GreaterThan extends TwoParamCommand {

    public GreaterThan(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 > (double) input2)? 1 : 0;
    }
}
