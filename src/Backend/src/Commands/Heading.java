package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class Heading extends ZeroParamCommand {
    public Heading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return myTurtle.getCurrentAngle();
    }
}
