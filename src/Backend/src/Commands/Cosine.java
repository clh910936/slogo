package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Cosine extends OneParamCommand {
    public Cosine(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians((double) input));
    }
}
