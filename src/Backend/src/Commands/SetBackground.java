package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class SetBackground extends OneParamCommand {

    public SetBackground(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        double d = Double.valueOf(String.valueOf(getMyParams().get(0)));
        int n = (int) d;
        getMyBackgroundModel().setCurrentBackgroundIndex(n);
        return n;
    }
}
