package Commands;

import Models.ModelManager;

public class And extends TwoParamCommand {

    public And(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return ((double) myParams.get(0) != 0 && (double) myParams.get(1) != 0)? 1 : 0;
    }

}
