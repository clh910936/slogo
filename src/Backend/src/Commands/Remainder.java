package Commands;

import Models.ModelManager;

public class Remainder extends TwoParamCommand {
    public Remainder(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) myParams.get(0) % (double) myParams.get(1);
    }
}
