package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class YCoordinate extends ZeroParamCommand {
    public YCoordinate (String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getUpdatedY();
    }
}
