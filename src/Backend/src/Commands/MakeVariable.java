package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        myVariablesModel.addVariable((String) input1, (String) input2);
        return (double) input2;
    }
}
