package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class NaturalLog extends OneParamCommand {
    public NaturalLog(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.log((double) myParams.get(0));
    }

}
