package Commands;

import Models.ModelManager;

public class And extends TwoParamCommand{

    public And(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return ((double) input1 != 0 && (double) input2 != 0)? 1 : 0;
    }

}
