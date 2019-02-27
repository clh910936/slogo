package Commands;

import BackExternal.IllegalParametersException;
import Models.VariablesModel;

public class MakeVariable extends TwoParamCommand {
    private VariablesModel variablesModel;

    public MakeVariable(String language) {
        super(language);
    }

    public void giveVariablesModel(VariablesModel vm) {
        this.variablesModel = vm;
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        variablesModel.addVariable((String) input1, (String) input2);
        return (double) input2;
    }
}
