package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class LessThan extends TwoParamCommand {

    public LessThan(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 < (double) input2)? 1 : 0;
    }
}
