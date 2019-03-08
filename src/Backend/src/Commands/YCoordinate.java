package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class YCoordinate extends ZeroParamCommand {
    public static final int STARTY = 2000;
    public YCoordinate (String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return myTurtle.getCurrentY() - STARTY;
    }
}
