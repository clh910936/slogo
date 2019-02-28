package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class GreaterThan extends TwoParamCommand {

    public GreaterThan(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 > (double) input2)? 1 : 0;
    }
}
