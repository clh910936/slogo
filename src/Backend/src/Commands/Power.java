package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Power extends TwoParamCommand {
    public Power(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.pow((double) getMyParams().get(0), (double) getMyParams().get(1));
    }
}
