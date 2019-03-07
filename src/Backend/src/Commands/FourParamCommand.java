package Commands;

import BackExternal.ModelManager;

public abstract class FourParamCommand extends CommandNode{

    public FourParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        MAX_PARAMS = 4;
    }
}
