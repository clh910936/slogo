package Commands;

import Models.VariablesModel;

public class MakeVariable extends TwoParamCommandStrings {
    private VariablesModel variablesModel;

    public MakeVariable() {
        super();
    }

    public void giveVariablesModel(VariablesModel vm) {
        this.variablesModel = vm;
    }

    @Override
    public double executeCommand() {
        variablesModel.addVariable(input1, Double.toString(input2));
        return input2;
    }
}
