package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;
import Parsing.SyntaxHandlerFactory;

public class YCoordinate extends ZeroParamCommand {
    public static final int STARTY = 2000;
    public YCoordinate (ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return myTurtle.getCurrentY() - STARTY;
    }
}
