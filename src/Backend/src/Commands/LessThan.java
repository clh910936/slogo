package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class LessThan extends TwoParamCommand {

    public LessThan(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        return (Double.valueOf(String.valueOf(myParams.get(0))) < Double.valueOf(String.valueOf(myParams.get(1))))? 1 : 0;
    }
}
