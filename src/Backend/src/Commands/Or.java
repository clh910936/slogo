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
        return (Double.parseDouble(getMyParams().get(0).toString()) != 0 || Double.parseDouble(getMyParams().get(1).toString()) != 0)? 1 : 0;
    }
}
