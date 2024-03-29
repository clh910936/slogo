package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class NaturalLog extends OneParamCommand {
    public NaturalLog(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Math.log(Double.parseDouble(getMyParams().get(0).toString()));
    }

}
