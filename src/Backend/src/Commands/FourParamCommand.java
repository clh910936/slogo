package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

/**
 * @author michaelzhang
 * Abstraction for command that requires four parameters
 */
public abstract class FourParamCommand extends CommandNode{

    /**
     * Constructor - sets max parameters and takes in modelManager
     * @param modelManager
     */
    public FourParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(4);
    }
}
