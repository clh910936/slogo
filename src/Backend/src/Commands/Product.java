package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Product extends TwoParamCommand {

    public Product(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) getMyParams().get(0) * (double) getMyParams().get(1);
    }
}
