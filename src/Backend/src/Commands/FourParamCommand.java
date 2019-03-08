package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class FourParamCommand extends CommandNode{

    public FourParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(4);
    }
}
