package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Turtles extends ZeroParamCommand {

    public Turtles(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return getMyTurtleModel().getAllTurtles().size();
    }
}
