package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Product extends TwoParamCommand {

    public Product(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 * (double) input2;
    }
}
