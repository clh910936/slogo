package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class Right extends OneParamCommand {

    public Right(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.turnCounterClockwise(-1 * Double.valueOf(String.valueOf(getMyParams().get(0))));
        return getMyParams().get(0);
    }
}
