package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        getMyVariablesModel().addVariable(String.valueOf(getMyParams().get(0)), String.valueOf(getMyParams().get(1)));
        return (double) getMyParams().get(1);
    }
}
