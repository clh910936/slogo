package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class ClearScreen extends ZeroParamCommand {

    public ClearScreen(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint(2000,2000);

        // TODO: talk to feroze

        return dist;
    }
}
