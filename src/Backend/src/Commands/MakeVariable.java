package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        myVariablesModel.addVariable(String.valueOf(myParams.get(0)), String.valueOf(myParams.get(1)));
        return (double) myParams.get(1);
    }
}
