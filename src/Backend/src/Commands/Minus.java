package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class Minus extends OneParamCommand {
    public Minus(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        return -1 * (double) myParams.get(0);
    }
}
