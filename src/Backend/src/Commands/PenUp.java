package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class PenUp extends ZeroParamCommand {

    public PenUp(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        return 0;
    }
}
