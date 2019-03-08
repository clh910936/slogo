package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class Forward extends OneParamCommand {

    public Forward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        myTurtle.moveForward(Double.valueOf(String.valueOf(getMyParams().get(0))));
        return getMyParams().get(0);
    }
}
