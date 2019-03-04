package Commands;

import BackExternal.ModelManager;

public class Constant extends ZeroParamCommand {

    private double num;

    public Constant(String language, ModelManager modelManager, double num) {
        super(language, modelManager);
        this.num = num;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return num;
    }
}
