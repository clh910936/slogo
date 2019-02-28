package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Difference extends TwoParamCommand {

    public Difference(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 - (double) input2;
    }
}
