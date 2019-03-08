package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Quotient extends TwoParamCommand {

    public Quotient(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) getMyParams().get(0) / (double) getMyParams().get(1);
    }
}
