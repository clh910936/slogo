package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Cosine extends OneParamCommand {
    public Cosine(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians((double) input));
    }
}
