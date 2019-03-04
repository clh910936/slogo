package Commands;

import BackExternal.ModelManager;

public class NotEqual extends TwoParamCommand {

    public NotEqual(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return ((double) myParams.get(0) != (double) myParams.get(1))? 1 : 0;
    }
}
