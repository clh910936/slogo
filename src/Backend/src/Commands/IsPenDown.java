package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return ! myTurtle.getCurrentIsPenUp() ? 1 : 0;
    }
}
