package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Tangent extends OneParamCommand {
    public Tangent(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) getMyParams().get(0)));
    }
}
