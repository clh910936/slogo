package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;


public abstract class TwoParamCommand extends CommandNode {

    public TwoParamCommand(ModelManager modelManager
) {
        super(modelManager
);
        setMaxParams(2);
    }




}
