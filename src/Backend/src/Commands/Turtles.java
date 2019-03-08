package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Turtles extends ZeroParamCommand {

    public Turtles(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return getMyTurtleModel().getAllTurtles().size();
    }
}
