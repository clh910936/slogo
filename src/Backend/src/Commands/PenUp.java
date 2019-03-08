package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class PenUp extends ZeroParamCommand {

    public PenUp(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.setPenUp();
        return 0;
    }
}
