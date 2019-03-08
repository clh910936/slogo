package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;

public abstract class ZeroParamCommand extends CommandNode {

    public ZeroParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(0);
    }

}
