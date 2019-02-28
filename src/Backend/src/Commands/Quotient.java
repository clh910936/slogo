package Commands;

import Models.ModelManager;

public class Quotient extends TwoParamCommand {

    public Quotient(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 / (double) input2;
    }
}
