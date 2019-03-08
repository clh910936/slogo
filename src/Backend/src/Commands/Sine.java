package Commands;

import BackExternal.ModelManager;

public class Sine extends OneParamCommand {

    public Sine(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.sin(Math.toRadians((double) getMyParams().get(0)));
    }
}
