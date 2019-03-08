package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Not extends OneParamCommand {

    public Not(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return ((double) getMyParams().get(0) == 0)? 1 : 0;
    }
}
