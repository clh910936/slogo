package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Tangent extends OneParamCommand {
    public Tangent(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
