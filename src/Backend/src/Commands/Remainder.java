package Commands;

import Models.ModelManager;

public class Remainder extends TwoParamCommand {
    public Remainder(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 % (double) input2;
    }
}
