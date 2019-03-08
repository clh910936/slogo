package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class And extends TwoParamCommand {

    public And(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (Double.valueOf(String.valueOf(getMyParams().get(0))) != 0 && Double.valueOf(String.valueOf(getMyParams().get(1))) != 0)? 1 : 0;
    }

}
