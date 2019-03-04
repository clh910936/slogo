package Commands;

import Models.ModelManager;


public abstract class TwoParamCommand extends CommandNode {

    public TwoParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        MAX_PARAMS = 2;
    }




}
