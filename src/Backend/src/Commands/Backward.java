package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class Backward extends OneParamCommand {

    public Backward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        myTurtle.moveForward(-1 * Double.valueOf(String.valueOf(getMyParams().get(0))));
        return (double) getMyParams().get(0);
    }
}
