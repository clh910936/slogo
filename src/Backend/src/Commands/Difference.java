package Commands;

import BackExternal.ModelManager;

public class Difference extends TwoParamCommand {

    public Difference(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Double.valueOf(String.valueOf(getMyParams().get(0))) - Double.valueOf(String.valueOf(getMyParams().get(1)));
    }
}
