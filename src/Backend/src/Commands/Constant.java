package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Constant extends ZeroParamCommand {

    private double num;

    public Constant(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager, double num) {
        super(syntaxHandlerFactory, modelManager);
        this.num = num;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return num;
    }
}
