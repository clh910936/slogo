package Commands;

import Models.ModelManager;

public class Sine extends OneParamCommand {

    public Sine(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return Math.sin(Math.toRadians((double) input));
    }
}
