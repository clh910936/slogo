package Commands;

import BackExternal.ModelManager;

/**
 * @author michaelzhang
 * Abstraction for command that requires zero parameters
 */
public abstract class ZeroParamCommand extends CommandNode {

    /**
     * Constructor - sets max parameters and takes in modelManager
     * @param modelManager
     */
    public ZeroParamCommand(ModelManager modelManager) {
        super(modelManager);
        setMaxParams(0);
    }

}
