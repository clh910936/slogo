package Commands;

import BackExternal.ModelManager;

public class Random extends OneParamCommand {
    public Random(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (int)(Math.random() * Double.valueOf(String.valueOf(getMyParams().get(0))));
    }
}
