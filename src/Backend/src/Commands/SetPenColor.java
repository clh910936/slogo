package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPenColor extends OneParamCommand {

    public SetPenColor(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        int n = (int) (double) Double.valueOf(String.valueOf(getMyParams().get(0)));
        myTurtle.setPenColor(n);
        return n;
    }
}
