package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetShape extends OneParamCommand {

    public SetShape(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        int n = (int) (double) Double.valueOf(String.valueOf(getMyParams().get(0)));
        myTurtle.setShapeIndex(n);
        return n;
    }
}
