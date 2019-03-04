package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Equal extends TwoParamCommand {

    public Equal(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        double one = Double.parseDouble(String.valueOf(input1));
        double two = Double.parseDouble(String.valueOf(input2));
        return (one == two) ? 1 : 0;
    }
}
