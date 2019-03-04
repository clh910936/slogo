package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();
        return ! myTurtle.getCurrentIsPenUp() ? 1 : 0;
    }
}
