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
        Turtle myTurtle = this.myTurtleModel.getCurrentTurtle();
        double d = Double.valueOf(String.valueOf(myParams.get(0)));
        int n = (int) d;
        myTurtle.setPenColor(n);
        return n;
    }
}
