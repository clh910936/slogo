package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Heading extends ZeroParamCommand {
    public Heading(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getHeadingAngle();
    }
}
