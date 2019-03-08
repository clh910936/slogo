package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class Left extends OneParamCommand {
    public Left(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.turnCounterClockwise(Double.valueOf(String.valueOf(getMyParams().get(0))));
        return getMyParams().get(0);
    }
}
