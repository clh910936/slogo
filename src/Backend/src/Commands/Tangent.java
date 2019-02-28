package Commands;

import Models.ModelManager;

public class Tangent extends OneParamCommand {
    public Tangent(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
