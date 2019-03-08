package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class ID extends ZeroParamCommand {

    public ID(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return this.getMyTurtleModel().getCurrentTurtleIndex();
    }
}
