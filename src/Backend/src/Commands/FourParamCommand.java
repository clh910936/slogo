package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class FourParamCommand extends CommandNode{

    public FourParamCommand(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
        setMaxParams(4);
    }
}
