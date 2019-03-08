package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class ClearScreen extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    public ClearScreen(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.clearScreen();
        getMyModelManager().resetTurtle();
        return dist;

    }
}
