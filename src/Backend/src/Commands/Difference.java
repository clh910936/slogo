package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Difference extends TwoParamCommand {

    public Difference(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return Double.valueOf(String.valueOf(myParams.get(0))) - Double.valueOf(String.valueOf(myParams.get(1)));
    }
}
