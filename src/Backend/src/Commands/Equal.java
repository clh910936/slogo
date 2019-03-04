package Commands;

import BackExternal.ModelManager;

public class Equal extends TwoParamCommand {

    public Equal(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        double one = Double.parseDouble(String.valueOf(myParams.get(0)));
        double two = Double.parseDouble(String.valueOf(myParams.get(1)));
        return (one == two) ? 1 : 0;
    }
}
