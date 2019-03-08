package Commands;

import Parsing.SyntaxHandlerFactory;
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

        return myTurtle.getCurrentIsDisplayed() ? 1 : 0;
    }
}
