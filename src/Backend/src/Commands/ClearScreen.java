package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class ClearScreen extends ZeroParamCommand {

    public ClearScreen(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint(2000,2000);

        // TODO: talk to feroze

        return dist;
    }
}
