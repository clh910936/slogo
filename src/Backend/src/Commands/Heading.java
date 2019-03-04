package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class Heading extends ZeroParamCommand {
    public Heading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();
        return myTurtle.getCurrentAngle();
    }
}
