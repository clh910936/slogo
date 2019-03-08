package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Random extends OneParamCommand {
    public Random(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return (int)(Math.random() * Double.valueOf(String.valueOf(getMyParams().get(0))));
    }
}
