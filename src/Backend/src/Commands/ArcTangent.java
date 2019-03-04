package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class ArcTangent extends OneParamCommand {
    public ArcTangent(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.atan(Math.toRadians((double) input));
    }
}
