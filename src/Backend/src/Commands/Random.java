package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Random extends OneParamCommand {
    public Random(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return (int)(Math.random() * Double.valueOf(String.valueOf(getMyParams().get(0))));
    }
}
