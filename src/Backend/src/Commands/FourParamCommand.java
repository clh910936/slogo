package Commands;

import BackExternal.ModelManager;

public abstract class FourParamCommand extends CommandNode{

    public FourParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        setMaxParams(4);
    }
}
