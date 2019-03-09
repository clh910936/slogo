package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;

public class NotEqual extends TwoParamCommand {

    public NotEqual(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (Double.parseDouble(getMyParams().get(0).toString()) != Double.parseDouble(getMyParams().get(1).toString()))? 1 : 0;
    }
}
