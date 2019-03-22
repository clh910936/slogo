package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

/**
 * @author michaelzhang
 * Abstraction for command that requires three parameters
 */
public abstract class ThreeParamCommand extends CommandNode {

    /**
     * Constructor - sets max parameters and takes in modelManager
     * @param modelManager
     */
    public ThreeParamCommand(ModelManager modelManager) {
        super(modelManager);
        setMaxParams(3);
    }




}
