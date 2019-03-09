package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class Backward extends OneParamCommand {

    public Backward(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.moveForward(-1 * Double.valueOf(String.valueOf(getMyParams().get(0))));
        return Double.parseDouble(getMyParams().get(0).toString());
    }
}
