package Commands;

import Models.ModelManager;

public abstract class OneParamCommand extends CommandNode {
    private static final int MAX_PARAMS = 1;
    protected Object input;
    public OneParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }




}
