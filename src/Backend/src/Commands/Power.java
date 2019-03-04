package Commands;

import BackExternal.ModelManager;

public class Power extends TwoParamCommand {
    public Power(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.pow((double) myParams.get(0), (double) myParams.get(1));
    }
}
