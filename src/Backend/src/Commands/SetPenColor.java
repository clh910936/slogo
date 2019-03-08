package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPenColor extends OneParamCommand {

    public SetPenColor(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        int n = (int) (double) Double.valueOf(String.valueOf(getMyParams().get(0)));
        myTurtle.setPenColor(n);
        return n;
    }
}
