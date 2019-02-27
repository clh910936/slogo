package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (myTurtle.getIsDisplayed()) ? 1 : 0;
    }
}
