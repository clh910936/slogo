package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class Left extends OneParamCommand {
    public Left(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.turnCounterClockwise(Double.valueOf(String.valueOf(getMyParams().get(0))));
        return getMyParams().get(0);
    }
}
