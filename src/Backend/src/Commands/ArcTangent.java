package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class ArcTangent extends OneParamCommand {
    public ArcTangent(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.atan(Math.toRadians(Double.valueOf(String.valueOf(myParams.get(0)))));
    }
}
