package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class NaturalLog extends OneParamCommand {
    public NaturalLog(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.log((double) getMyParams().get(0));
    }

}
