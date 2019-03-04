package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class GreaterThan extends TwoParamCommand {

    public GreaterThan(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        return ((double) myParams.get(0) > (double) myParams.get(1))? 1 : 0;
    }
}
