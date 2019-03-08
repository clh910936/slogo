package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class ArcTangent extends OneParamCommand {
    public ArcTangent(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.atan(Math.toRadians(Double.valueOf(String.valueOf(getMyParams().get(0)))));
    }
}
