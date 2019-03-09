package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetShape extends OneParamCommand {

    public SetShape(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        int n = (int) Double.parseDouble(getMyParams().get(0).toString());
        myTurtle.setShapeIndex(n);
        return n;
    }
}
