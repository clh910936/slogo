package Commands;

import BackExternal.ModelManager;

/**
 * @author michaelzhang
 * Abstraction for command that requires zero parameters
 */
public abstract class TwoParamCommand extends CommandNode {

    /**
     * Constructor - sets max parameters and takes in modelManager
     * @param modelManager
     */
    public TwoParamCommand(ModelManager modelManager) {
        super(modelManager);
        setMaxParams(2);
    }

}
