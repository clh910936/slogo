package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public abstract class ThreeParamCommand extends CommandNode {

    public ThreeParamCommand(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
        setMaxParams(3);
    }




}
