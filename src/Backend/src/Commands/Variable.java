package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Variable extends ZeroParamCommand {

    private String myVarName;

    public Variable(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager, String varName) {
        super(syntaxHandlerFactory, modelManager);
        this.myVarName = varName;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return getMyVariablesModel().getVariable(myVarName);
    }
}
