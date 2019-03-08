package Commands;

import BackExternal.ModelManager;

public abstract class ZeroParamCommand extends CommandNode {

    public ZeroParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        setMaxParams(0);
    }

}
