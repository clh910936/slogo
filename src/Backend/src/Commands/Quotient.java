package Commands;

import Models.ModelManager;

public class Quotient extends TwoParamCommand {

    public Quotient(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) myParams.get(0) / (double) myParams.get(1);
    }
}
