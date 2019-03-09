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
        return Double.parseDouble(getMyParams().get(0).toString()) / Double.parseDouble(getMyParams().get(1).toString());
    }
}
