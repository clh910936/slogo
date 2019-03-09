package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        return myTurtle.getMyIsDisplayed() ? 1 : 0;
    }
}
