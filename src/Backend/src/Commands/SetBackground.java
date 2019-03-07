package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

public class SetBackground extends OneParamCommand {

    public SetBackground(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        myBackgroundModel.setCurrentBackgroundIndex(Integer.valueOf(String.valueOf(myParams.get(0))));
        return Integer.valueOf(String.valueOf(myParams.get(0)));
    }
}
