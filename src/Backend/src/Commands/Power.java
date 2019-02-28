package Commands;

import Models.ModelManager;

public class Power extends TwoParamCommand {
    public Power(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return Math.pow((double) input1, (double) input2);
    }
}
