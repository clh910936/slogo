package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class Minus extends OneParamCommand {
    public Minus(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        return -1 * (double) getMyParams().get(0);
    }
}
