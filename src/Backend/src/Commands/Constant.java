package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Constant extends ZeroParamCommand {

    private double num;

    public Constant(ModelManager modelManager
, double num) {
        super(modelManager
);
        this.num = num;
    }


    @Override
    public Object executeCommand() throws ClassCastException {
        return num;
    }
}
