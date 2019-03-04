package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return ! myTurtle.getCurrentIsPenUp() ? 1 : 0;
    }
}
