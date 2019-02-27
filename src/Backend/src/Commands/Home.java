package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Home extends ZeroParamCommand {

    public Home(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(0,0);
        myTurtle.updatePoints(0,0);
        myTurtle.setPenDown();
        return dist;
    }
}
