package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class ThreeParamCommand extends CommandNode {

    public ThreeParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(3);
    }




}
