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
        return Math.pow(Double.parseDouble(getMyParams().get(0).toString()), Double.parseDouble(getMyParams().get(1).toString()));
    }
}
