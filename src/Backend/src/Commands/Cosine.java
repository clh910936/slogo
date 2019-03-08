package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Cosine extends OneParamCommand {
    public Cosine(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians(Double.valueOf(String.valueOf(getMyParams().get(0)))));
    }
}
