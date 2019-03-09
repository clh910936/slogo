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
        return Double.parseDouble(getMyParams().get(0).toString()) % Double.parseDouble(getMyParams().get(1).toString());
    }
}
