package Commands;

import BackExternal.ModelManager;

public abstract class OneParamCommand extends CommandNode {
    public OneParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        MAX_PARAMS = 1;
    }





}
