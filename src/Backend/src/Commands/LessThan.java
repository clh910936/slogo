package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class LessThan extends TwoParamCommand {

    public LessThan(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        return ((double) myParams.get(0) < (double) myParams.get(1))? 1 : 0;
    }
}
