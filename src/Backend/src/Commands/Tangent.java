package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Tangent extends OneParamCommand {
    public Tangent(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
