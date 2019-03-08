package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Difference extends TwoParamCommand {

    public Difference(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Double.valueOf(String.valueOf(getMyParams().get(0))) - Double.valueOf(String.valueOf(getMyParams().get(1)));
    }
}
