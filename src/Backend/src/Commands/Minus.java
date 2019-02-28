package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Minus extends OneParamCommand {
    public Minus(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return -1 * (double) input;
    }
}
