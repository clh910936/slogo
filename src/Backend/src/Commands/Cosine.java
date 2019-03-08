package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Cosine extends OneParamCommand {
    public Cosine(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians(Double.valueOf(String.valueOf(getMyParams().get(0)))));
    }
}
