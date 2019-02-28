package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (myTurtle.getIsDisplayed().get(myTurtle.getIsDisplayed().size()-1)) ? 1 : 0;
    }
}
