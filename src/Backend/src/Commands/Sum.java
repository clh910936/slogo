package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Sum extends TwoParamCommand {
    public Sum(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return (double) getMyParams().get(0) + (double) getMyParams().get(1);
    }
}
