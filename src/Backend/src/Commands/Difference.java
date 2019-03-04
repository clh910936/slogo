package Commands;

import BackExternal.ModelManager;

public class Difference extends TwoParamCommand {

    public Difference(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) myParams.get(0) - (double) myParams.get(1);
    }
}
