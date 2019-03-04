package Commands;

import BackExternal.ModelManager;

public class Cosine extends OneParamCommand {
    public Cosine(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians((double) myParams.get(0)));
    }
}
