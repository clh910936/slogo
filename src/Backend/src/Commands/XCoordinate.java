package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class XCoordinate extends ZeroParamCommand {
    public static final int STARTX = 2000;
    public XCoordinate(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return myTurtle.getCurrentX() - STARTX;
    }
}
