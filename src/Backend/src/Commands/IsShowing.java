package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        return myTurtle.getCurrentIsDisplayed() ? 1 : 0;
    }
}
