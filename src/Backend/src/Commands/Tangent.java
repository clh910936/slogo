package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Tangent extends OneParamCommand {
    public Tangent(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians(Double.parseDouble(getMyParams().get(0).toString())));
    }
}
