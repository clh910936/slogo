package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (! myTurtle.getIsPenUp()) ? 1 : 0;
    }
}
