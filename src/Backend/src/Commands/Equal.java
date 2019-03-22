package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class Equal extends TwoParamCommand {

    public Equal(ModelManager modelManager) {
        super(modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        double one = Double.parseDouble(String.valueOf(getMyParams().get(0)));
        double two = Double.parseDouble(String.valueOf(getMyParams().get(1)));
        return (one == two) ? 1 : 0;
    }
}
