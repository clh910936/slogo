package Commands;

import BackExternal.ModelManager;

public class Product extends TwoParamCommand {

    public Product(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) getMyParams().get(0) * (double) getMyParams().get(1);
    }
}
