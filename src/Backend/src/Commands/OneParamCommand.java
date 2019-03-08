package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class OneParamCommand extends CommandNode {
    public OneParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(1);
    }





}
