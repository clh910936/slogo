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
        return -1 * Double.parseDouble(getMyParams().get(0).toString());
    }
}
