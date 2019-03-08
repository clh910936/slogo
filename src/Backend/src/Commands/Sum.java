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
        System.out.println((double) getMyParams().get(0) + (double) getMyParams().get(1));
        return (double) getMyParams().get(0) + (double) getMyParams().get(1);
    }
}
