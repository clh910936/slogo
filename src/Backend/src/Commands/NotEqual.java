package Commands;

import Models.ModelManager;

public class NotEqual extends TwoParamCommand {

    public NotEqual(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return ((double) input1 != (double) input2)? 1 : 0;
    }
}
