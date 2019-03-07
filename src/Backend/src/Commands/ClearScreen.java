package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class ClearScreen extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    public ClearScreen(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.clearScreen();
        myModelManager.resetTurtle();
        return dist;

    }
}
