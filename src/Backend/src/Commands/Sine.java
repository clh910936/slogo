package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Sine extends OneParamCommand {

    public Sine(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.sin(Math.toRadians((double) getMyParams().get(0)));
    }
}
