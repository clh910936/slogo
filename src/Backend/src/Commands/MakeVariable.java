package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;

public class MakeVariable extends TwoParamCommand {

    public MakeVariable(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        myVariablesModel.addVariable(String.valueOf(input1), String.valueOf(input2));
        return (double) input2;
    }
}
