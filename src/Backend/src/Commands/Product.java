package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Product extends TwoParamCommand {

    public Product(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 * (double) input2;
    }
}
