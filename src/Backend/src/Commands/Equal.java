package Commands;

import Models.ModelManager;

public class Equal extends TwoParamCommand {

    public Equal(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (input1 == input2)? 1 : 0;
    }
}
