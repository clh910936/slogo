package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class LessThan extends TwoParamCommand {

    public LessThan(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        return (Double.valueOf(String.valueOf(getMyParams().get(0))) < Double.valueOf(String.valueOf(getMyParams().get(1))))? 1 : 0;
    }
}
