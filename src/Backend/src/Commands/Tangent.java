package Commands;

import BackExternal.ModelManager;

public class Tangent extends OneParamCommand {
    public Tangent(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) getMyParams().get(0)));
    }
}
