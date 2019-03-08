package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Or extends TwoParamCommand {

    public Or(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return ((double) getMyParams().get(0) != 0 || (double) getMyParams().get(1) != 0)? 1 : 0;
    }
}
