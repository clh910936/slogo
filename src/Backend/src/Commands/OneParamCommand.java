package Commands;

import Models.ModelManager;

public abstract class OneParamCommand extends CommandNode {
    private static final int MAX_PARAMS = 1;
    public OneParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return myChildren.size() == MAX_PARAMS;
    }



}
