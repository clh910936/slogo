package Commands;

import BackExternal.ModelManager;

public class Power extends TwoParamCommand {
    public Power(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.pow((double) getMyParams().get(0), (double) getMyParams().get(1));
    }
}
