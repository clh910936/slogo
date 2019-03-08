package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class OneParamCommand extends CommandNode {
    public OneParamCommand(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
        setMaxParams(1);
    }





}
