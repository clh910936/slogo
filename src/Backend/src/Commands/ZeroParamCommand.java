package Commands;

import Models.ModelManager;

public abstract class ZeroParamCommand extends CommandNode {

    public ZeroParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }
}
