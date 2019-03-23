package Commands;

import BackExternal.ModelManager;

/**
 * @author michaelzhang
 * Abstraction for command that requires one parameter
 */
public abstract class OneParamCommand extends CommandNode {

    /**
     * Constructor - sets max parameters and takes in modelManager
     * @param modelManager
     */
    public OneParamCommand(ModelManager modelManager) {
        super(modelManager);
        setMaxParams(1);
    }
}
