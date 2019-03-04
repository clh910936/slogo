package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myTurtle.getCurrentIsDisplayed() ? 1 : 0;
    }
}
