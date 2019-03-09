package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Sum extends TwoParamCommand {
    public Sum(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return Double.parseDouble(getMyParams().get(0).toString()) + Double.parseDouble(getMyParams().get(1).toString());
    }
}
