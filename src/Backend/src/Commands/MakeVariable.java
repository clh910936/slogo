package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        myVariablesModel.addVariable((String) input1, (String) input2);
        return (double) input2;
    }
}
