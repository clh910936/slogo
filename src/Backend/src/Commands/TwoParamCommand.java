package Commands;

import BackExternal.ModelManager;


public abstract class TwoParamCommand extends CommandNode {

    public TwoParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        setMaxParams(2);
    }




}
