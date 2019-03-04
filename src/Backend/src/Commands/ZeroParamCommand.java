package Commands;

import Models.ModelManager;

public abstract class ZeroParamCommand extends CommandNode {

    public ZeroParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        MAX_PARAMS = 0;
    }

}
