package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Not extends OneParamCommand {

    public Not(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return (Double.parseDouble(getMyParams().get(0).toString()) == 0)? 1 : 0;
    }
}
