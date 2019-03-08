package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;

public class NotEqual extends TwoParamCommand {

    public NotEqual(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return ((double) getMyParams().get(0) != (double) getMyParams().get(1))? 1 : 0;
    }
}
