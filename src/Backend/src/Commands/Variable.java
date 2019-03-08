package Commands;

import BackExternal.ModelManager;

public class Variable extends ZeroParamCommand {

    private String myVarName;

    public Variable(String language, ModelManager modelManager, String varName) {
        super(language, modelManager);
        this.myVarName = varName;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return getMyVariablesModel().getVariable(myVarName);
    }
}
