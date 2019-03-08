package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Remainder extends TwoParamCommand {
    public Remainder(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) getMyParams().get(0) % (double) getMyParams().get(1);
    }
}
