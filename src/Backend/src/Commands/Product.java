package Commands;

import Models.ModelManager;

public class Product extends TwoParamCommand {

    public Product(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (double) input1 * (double) input2;
    }
}
