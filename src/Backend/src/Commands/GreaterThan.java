package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class GreaterThan extends TwoParamCommand {

    public GreaterThan(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 > (double) input2)? 1 : 0;
    }
}
