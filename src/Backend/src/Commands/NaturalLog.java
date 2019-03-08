package Commands;

import BackExternal.ModelManager;

public class NaturalLog extends OneParamCommand {
    public NaturalLog(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.log((double) getMyParams().get(0));
    }

}
