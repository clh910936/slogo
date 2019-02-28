package Commands;

import BackExternal.IllegalParametersException;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class LessThan extends TwoParamCommand {

    public LessThan(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 < (double) input2)? 1 : 0;
    }
}
