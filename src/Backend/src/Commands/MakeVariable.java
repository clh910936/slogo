package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        myVariablesModel.addVariable(String.valueOf(getMyParams().get(0)), String.valueOf(getMyParams().get(1)));
        return (double) getMyParams().get(1);
    }
}
