package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (myTurtle.getIsDisplayed()) ? 1 : 0;
    }
}
