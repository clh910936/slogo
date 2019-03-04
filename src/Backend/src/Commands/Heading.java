package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Heading extends ZeroParamCommand {
    public Heading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myTurtle.getCurrentAngle();
    }
}
