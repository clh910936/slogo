package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Tangent extends OneParamCommand {
    public Tangent(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
