package Commands;

import Models.ModelManager;


public abstract class TwoParamCommand extends CommandNode {
    private static final int MAX_PARAMS = 2;

    public TwoParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return myChildren.size() == MAX_PARAMS;
    }


}
