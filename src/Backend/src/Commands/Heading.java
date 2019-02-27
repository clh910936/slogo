package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Heading extends ZeroParamCommand {
    public Heading(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getHeadingAngle();
    }
}
